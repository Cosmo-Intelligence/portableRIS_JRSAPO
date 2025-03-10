package ris.lib.core.bean;

import java.sql.Timestamp;

import ris.portable.common.Const;

/// <summary>
/// AttrManageBean の概要の説明です。
/// </summary>
public class AttrManageBean
{
    // private members
    private String attrIDStr		= ""; 					//AttrManage.ATTRID
    private String attrOwnerIDStr	= ""; 					//AttrManage.ATTROWNERID
    private String attrNameStr		= ""; 					//AttrManage.ATTRNAME
    private String valueTypeStr		= "";					//AttrManage.VALUETYPE
    private String textValueStr		= "";					//AttrManage.TEXTVALUE
    private Timestamp updateTimestamp = Const.TIMESTAMP_MINVALUE;	//AttrManage.UPDATEDATETIME

	/// <summary>
	/// コンストラクタ
	/// </summary>
    public AttrManageBean()
    {
        //
    }
    //
    // attrIDStrのSET
    public void SetAttrID(String attrID)
    {
        if (attrID != null)
        {
            this.attrIDStr = attrID;
        }
    }
    // attrIDStrのGET
    public String GetAttrID()
    {
        return this.attrIDStr;
    }
    //
    // attrOwnerIDStrのSET
    public void SetAttrOwnerID(String ownerID)
    {
        if (ownerID != null)
        {
            this.attrOwnerIDStr = ownerID;
        }
    }
    // attrOwnerIDStrのGET
    public String GetAttrOwnerID()
    {
        return this.attrOwnerIDStr;
    }
    //
    // attrNameStrのSET
    public void SetAttrName(String attrName)
    {
        if (attrName != null)
        {
            this.attrNameStr = attrName;
        }
    }
    // attrNameStrのGET
    public String GetAttrName()
    {
        return this.attrNameStr;
    }
    //
    // valueTypeStrのSET
    public void SetValueType(String type)
    {
        if (type != null)
        {
            this.valueTypeStr = type;
        }
    }
    // valueTypeStrのGET
    public String GetValueType()
    {
        return this.valueTypeStr;
    }
    //
    // textValueStrのSET
    public void SetTextValue(String value)
    {
        if (value != null)
        {
            this.textValueStr = value;
        }
    }
    // textValueStrのGET
    public String GetTextValue()
    {
        return this.textValueStr;
    }
    //
    // updateTimestampのSET
    public void SetUpdateTimestamp(Timestamp updateDT)
    {
        if (!Const.TIMESTAMP_MINVALUE.equals(updateDT))
        {
            this.updateTimestamp = updateDT;
        }
    }
    // updateTimestampのGET
    public Timestamp GetUpdateTimestamp()
    {
        return this.updateTimestamp;
    }
}
