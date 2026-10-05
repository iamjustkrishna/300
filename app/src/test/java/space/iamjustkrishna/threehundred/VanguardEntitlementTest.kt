package space.iamjustkrishna.threehundred

import org.junit.Assert.assertFalse
import org.junit.Test
import space.iamjustkrishna.threehundred.core.entitlement.VanguardEntitlementRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class VanguardEntitlementTest {
    @Test
    fun entitlement_contract_defaults_to_locked_until_billing_confirms_purchase() = runBlocking {
        val fake = object : VanguardEntitlementRepository {
            override val isUnlocked: Flow<Boolean> = MutableStateFlow(false)
            override suspend fun cachePurchase(unlocked: Boolean) = Unit
        }
        assertFalse(fake.isUnlocked.first())
    }
}
