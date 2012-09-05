package model.net;

/**
 * wrapper for ConnectPool
 * @author skuarch
 */
public class ConnectAction extends ConnectPool{
    
    //==========================================================================
    /**
     * create a instance, 
     * for get the <code>DataSource</code> is using <code>ControllerMain</code>
     */
    public ConnectAction(){
        super(controllers.ssn.ControllerMain.getDataSource());        
    }    
    
} // end
