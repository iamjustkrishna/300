package space.iamjustkrishna.threehundred.core.entitlement

import kotlinx.coroutines.flow.Flow
import space.iamjustkrishna.threehundred.core.preferences.VanguardPreferences

interface VanguardEntitlementRepository {
    val isUnlocked: Flow<Boolean>
    suspend fun cachePurchase(unlocked: Boolean)
}

class OfflineVanguardEntitlementRepository(
    private val preferences: VanguardPreferences
) : VanguardEntitlementRepository {
    override val isUnlocked: Flow<Boolean> = preferences.isVanguardUnlocked

    override suspend fun cachePurchase(unlocked: Boolean) {
        preferences.setVanguardUnlocked(unlocked)
    }
}
