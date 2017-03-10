package t;

import com.connectto.wallet.merchant.business.wallet.IWalletManager;
import com.connectto.wallet.merchant.dataaccess.merchant.dao.ICompanyCashBoxDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Serozh on 12/21/2016.
 */
public class CompanyCashBoxTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        ICompanyCashBoxDao companyCashBoxDao = (ICompanyCashBoxDao) context.getBean("CompanyCashBoxDao");
        IWalletManager walletManager = (IWalletManager) context.getBean("WalletManagerImpl");

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("companyId", 39);
        try {
            int as = walletManager.getCountByParams(params);
            System.out.println(as);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
