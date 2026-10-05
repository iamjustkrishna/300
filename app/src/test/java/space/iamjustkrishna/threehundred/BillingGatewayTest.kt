package space.iamjustkrishna.threehundred

import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import space.iamjustkrishna.threehundred.core.entitlement.PurchaseState
import space.iamjustkrishna.threehundred.core.entitlement.UnconfiguredBillingGateway

class BillingGatewayTest {
    @Test
    fun unconfigured_gateway_surfaces_failure_instead_of_granting_access() = runTest {
        val result = UnconfiguredBillingGateway().purchaseVanguard()
        assertEquals(PurchaseState.FAILED, result.state)
    }
}
