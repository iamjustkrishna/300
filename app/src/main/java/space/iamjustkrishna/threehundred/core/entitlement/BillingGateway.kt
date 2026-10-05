package space.iamjustkrishna.threehundred.core.entitlement

import kotlinx.coroutines.flow.Flow

enum class PurchaseState {
    IDLE,
    PENDING,
    PURCHASED,
    RESTORED,
    FAILED
}

data class PurchaseResult(
    val state: PurchaseState,
    val message: String? = null
)

interface BillingGateway {
    val purchaseState: Flow<PurchaseResult>
    suspend fun purchaseVanguard(): PurchaseResult
    suspend fun restorePurchases(): PurchaseResult
}

class UnconfiguredBillingGateway : BillingGateway {
    override val purchaseState: Flow<PurchaseResult> =
        kotlinx.coroutines.flow.flowOf(PurchaseResult(PurchaseState.IDLE))

    override suspend fun purchaseVanguard(): PurchaseResult =
        PurchaseResult(PurchaseState.FAILED, "Google Play Billing is not configured.")

    override suspend fun restorePurchases(): PurchaseResult =
        PurchaseResult(PurchaseState.FAILED, "Google Play Billing is not configured.")
}
