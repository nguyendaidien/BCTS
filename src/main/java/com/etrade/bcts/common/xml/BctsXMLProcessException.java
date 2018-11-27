/**
 * 
 */
package com.etrade.bcts.common.xml;

/**
 * Thrown if encountered failure during the XML operation.
 * 
 * @author weixiang
 * @since 5.0.0
 */
public class BctsXMLProcessException extends Exception
{
    /** 
     * 
     */
    private static final long serialVersionUID = 7849912204331589140L;

    public BctsXMLProcessException(String msg)
    {
        super(msg);
    }

    public BctsXMLProcessException(String msg, Throwable th)
    {
        super(msg, th);
    }
}
