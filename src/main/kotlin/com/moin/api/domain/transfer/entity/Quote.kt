package com.moin.api.domain.transfer.entity

import com.fin.best.bestfin.api.component.constants.AppConst
import jakarta.persistence.*
import org.hibernate.annotations.Comment
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Comment("견적 테이블")
class Quote(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    @Comment("견적 IDX")
    var quoteIdx: Long = 0L,

    @Column(nullable = false)
    @Comment("회원 IDX")
    var userIdx: Long = 0L,

    @Column(nullable = false, length = 10)
    @Comment("상태 코드 (WAIT, COMPLETE)")  /* 만료, 취소 등 이후 flow 생략 */
    var statusCode: String = AppConst.Transfer.StatusCode.WAIT.code,

    /* TODO : 정책 방향에 따라 추가 고려 */
//    @Column(nullable = false, length = 3)
//    @Comment("대상 화폐 (KRW)")
//    var sourceCurrency: String = AppConst.Transfer.Currency.USD.code,

    @Column(nullable = false, length = 3)
    @Comment("대상 화폐 (USD, JPY)")
    var targetCurrency: String = AppConst.Transfer.Currency.USD.code,

    @Column(nullable = false)
    @Comment("환율")
    var exchangeRate: BigDecimal = BigDecimal.ZERO,

    @Column(nullable = false)
    @Comment("화폐단위")
    var currencyUnit: Int = 0,

    @Column(nullable = false)
    @Comment("고정 수수료 -KRW")
    var fiexedFee: Long = 0,

    @Column(nullable = false)
    @Comment("수수료 비율")
    var feeRate: BigDecimal = BigDecimal.ZERO,

    @Column(nullable = false)
    @Comment("총 수수료")
    var totalFee: BigDecimal = BigDecimal.ZERO,

    @Column(nullable = false)
    @Comment("송금 금액 -KRW")
    var sourceAmount: Long = 0L,

    @Column(nullable = false)
    @Comment("대상 금액")
    var targetAmount: BigDecimal = BigDecimal.ZERO,

    @Column(nullable = false)
    @Comment("만료 일시")
    var expireTime: LocalDateTime = LocalDateTime.now(),

    @Column(nullable = false)
    @Comment("등록 일시")
    var regTime: LocalDateTime = LocalDateTime.now(),

    @Comment("요청 일시")
    var requestedDate: LocalDateTime? = null,

    )