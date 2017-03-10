import com.connectto.wallet.merchant.business.merchant.ICompanyManager;
import com.connectto.wallet.merchant.business.wallet.IWalletManager;
import com.connectto.wallet.merchant.common.data.wallet.Wallet;
import com.connectto.wallet.merchant.common.exception.DataParseException;
import com.connectto.wallet.merchant.common.exception.EntityNotFoundException;
import com.connectto.wallet.merchant.common.exception.InternalErrorException;
import com.connectto.wallet.merchant.common.util.DataConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Map;

/**
 * Created by Serozh on 9/23/2016.
 */
public class WalletTest {
    public static void main(String[] args) throws DataParseException, InternalErrorException, EntityNotFoundException {
        String requestJson = "{\"page\":1,\"count\":10,\"orderBy\":\"surname\",\"orderType\":\"desc\"}";
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        IWalletManager walletManager = (IWalletManager) context.getBean("WalletManagerImpl");
        ICompanyManager companyManager = (ICompanyManager) context.getBean("CompanyManagerImpl");
        Map<String, Object> params = DataConverter.convertWalletRequestToParams(requestJson);

        long page = (Long) params.get("page");
        long count = (Long) params.get("count");
        params.put("page", (page - 1) * count);

        /*List<Wallet> wallets = walletManager.getByParams(params);
        for(Wallet wallet : wallets){
            System.out.println(wallet);
        }*/

        System.out.println(companyManager.getByName("abc  efg"));
    }
}
