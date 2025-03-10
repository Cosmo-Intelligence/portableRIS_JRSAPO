// オーダ情報
select
	r.RIS_ID,
	r.KANJA_ID,
	r.STATUS,
	r.SEX,
	r.KANJISIMEI,
	r.KANASIMEI,
	r.BYOUTOU,
	r.BYOUSITU,TRUNC((TO_NUMBER(TO_CHAR(SYSDATE,'yyyymmdd')) - r.BIRTHDAY)/10000,0) as AGE,
	r.IRAI_SECTION,
	r.IRAI_DOCTOR_NAME || ' ' || r.IRAIDOCTOR_TEL as IRAI_DOCTOR_NAME,
	e.RENRAKU_MEMO
from
	RISSUMMARYVIEW r,
	EXMAINTABLE e
where
	r.RIS_ID = e.RIS_ID
and
	r.KANJA_ID = :KANJA_ID
/*
and
	r.UKETUKE_TANTOU_ID = :UKETUKE_TANTOU_ID
and
	r.BYOUTOU_ID = :BYOUTOU_ID
*/
and
	r.KENSATYPE_ID = :KENSATYPE_ID
/*
and
	r.STATUS in (0,1,2)
*/
and
	(
		(r.EX_KENSA_DATE = :KENSA_DATE and r.STATUS >= 10)
	or
		(r.ORDER_KENSA_DATE = :KENSA_DATE and r.STATUS < 10)
	)
order by
/*
 	①未受
		r.ORDER_KENSA_DATE asc
	②受付
		r.BYOUTOU asc,KANASIMEI asc
	③保留
		r.EXAMSTARTDATE asc
	④検済
		r.EXAMENDDATE asc
*/
