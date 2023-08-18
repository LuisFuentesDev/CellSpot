package com.example.phonenew

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.room.Room
import com.google.common.truth.Truth.assertThat
import androidx.test.core.app.ApplicationProvider
import com.example.phonenew.data.local.PhoneDao
import com.example.phonenew.data.local.PhoneDataBase
import com.example.phonenew.data.local.PhoneEntity
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

class PhoneRoomDataBaseTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var cellPhoneDAO: PhoneDao
    private lateinit var cellPhoneDataBase: PhoneDataBase

    @Before
    fun setUp() {
        //Fake
        val context = ApplicationProvider.getApplicationContext<Context>()
        cellPhoneDataBase =
            Room.inMemoryDatabaseBuilder(context, PhoneDataBase::class.java).build()
        cellPhoneDAO = cellPhoneDataBase.getPhoneDao()
    }

    @After
    fun tearDown() {
        cellPhoneDataBase.close()
    }

    @Test
    fun insertPhones_empty() = runBlocking {
        // Given
        val cellPhoneList = listOf<PhoneEntity>()

        // When
        cellPhoneDAO.insertPhone(cellPhoneList)

        // Then A
        val it = cellPhoneDAO.getPhone().getOrAwaitValue()

        assertThat(it).isNotNull()
        assertThat(it).isEmpty()


        // Then B
        cellPhoneDAO.getPhone().observeForever {
            assertThat(it).isNotNull()
            assertThat(it).isEmpty()
        }
    }

    @Test
    fun insertPhones_happyCase_1element() = runBlocking {
        // Given
        val cellPhoneList = listOf(PhoneEntity(1, "Xiaomi", 1000000, "www.cellphone.com"))

        // When
        cellPhoneDAO.insertPhone(cellPhoneList)

        // Then
        cellPhoneDAO.getPhone().observeForever {
            assertThat(it).isNotNull()
            assertThat(it).isNotEmpty()
            assertThat(it).hasSize(1)
        }
    }

    @Test
    fun insertPhones_happyCase_3elements() = runBlocking {
        // Given
        val cellPhoneList =
            listOf(
                PhoneEntity(1, "Iphone", 800000, "www.cellphone1.com"),
                PhoneEntity(2, "Nokia", 700000, "www.cellphone2.com"),
                PhoneEntity(3, "Xiaomi", 600000, "www.cellphone3.com")
            )

        // When
        cellPhoneDAO.insertPhone(cellPhoneList)

        // Then
        cellPhoneDAO.getPhone().observeForever {
            assertThat(it).isNotNull()
            assertThat(it).isNotEmpty()
            assertThat(it).hasSize(3)
        }
    }


    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    fun <T> LiveData<T>.getOrAwaitValue(
        time: Long = 2,
        timeUnit: TimeUnit = TimeUnit.SECONDS,
        afterObserve: () -> Unit = {}
    ): T {
        var data: T? = null
        val latch = CountDownLatch(1)
        val observer = object : Observer<T> {
            override fun onChanged(value: T) {
                data = value
                latch.countDown()
                this@getOrAwaitValue.removeObserver(this)
            }
        }
        this.observeForever(observer)

        try {
            afterObserve.invoke()

            // Don't wait indefinitely if the LiveData is not set.
            if (!latch.await(time, timeUnit)) {
                throw TimeoutException("LiveData value was never set.")
            }

        } finally {
            this.removeObserver(observer)
        }

        @Suppress("UNCHECKED_CAST")
        return data as T
    }
}

