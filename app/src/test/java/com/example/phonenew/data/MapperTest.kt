package com.example.phonenew.data

import com.example.phonenew.data.remote.DataPhone
import com.example.phonenew.data.remote.DataPhoneDetails
import org.junit.Assert.assertEquals
import org.junit.Test

class MapperTest {

    @Test
    fun transformToEntity() {
        //Given
        val cellPhone = DataPhone(1, "Redmi note 9", 50000, "wwww.image.cl")

        //When
        val resultadoTransformacionCellPhone = cellPhone.transformToEntity()

        //Then
        assertEquals(cellPhone.id, resultadoTransformacionCellPhone.id)
        assertEquals(cellPhone.name, resultadoTransformacionCellPhone.name)
        assertEquals(cellPhone.price, resultadoTransformacionCellPhone.price)
        assertEquals(cellPhone.image, resultadoTransformacionCellPhone.image)
    }

    @Test
    fun transformToDetailEntity() {
        //Given
        val cellPhoneDetail =
            DataPhoneDetails(1, "Iphone pro max 12", 80000, "wwww.image.cl", "Resistente al agua", 1200000, true)

        //When
        val resultadoTransformacionCellPhoneDetail = cellPhoneDetail.transformToDetailEntity()

        //Then
        assertEquals(cellPhoneDetail.id, resultadoTransformacionCellPhoneDetail.id)
        assertEquals(cellPhoneDetail.name, resultadoTransformacionCellPhoneDetail.name)
        assertEquals(cellPhoneDetail.price, resultadoTransformacionCellPhoneDetail.price)
        assertEquals(cellPhoneDetail.image, resultadoTransformacionCellPhoneDetail.image)
        assertEquals(
            cellPhoneDetail.description,
            resultadoTransformacionCellPhoneDetail.description
        )
        assertEquals(cellPhoneDetail.lastPrice, resultadoTransformacionCellPhoneDetail.lastPrice)
        assertEquals(cellPhoneDetail.credit, resultadoTransformacionCellPhoneDetail.credit)
    }
}