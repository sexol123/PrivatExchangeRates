package vn.oleksandr.sandul.privatexchangerates

import org.junit.Test
import vn.oleksandr.sandul.privatexchangerates.utils.Api
import vn.oleksandr.sandul.privatexchangerates.utils.withJsonLog

class ApiUnitTest {

    @Test
    fun `get cash exchange rate`() {
        Api().apiCalls.getCashExchangeRate()
                .withJsonLog()
                .test()
                .assertNoErrors()
                .assertValue {
                    it[0].baseCcy.isNotEmpty()
                    it[0].buy.isNotEmpty()
                    it[0].sale.isNotEmpty()
                    it[0].ccy.isNotEmpty()
                }
    }

    @Test
    fun `get non cash exchange rate`() {
        Api().apiCalls.getNonCashExchangeRate()
                .withJsonLog()
                .test()
                .assertNoErrors()
                .assertValue {
                    it[0].baseCcy.isNotEmpty()
                    it[0].buy.isNotEmpty()
                    it[0].sale.isNotEmpty()
                    it[0].ccy.isNotEmpty()
                }
    }
}
