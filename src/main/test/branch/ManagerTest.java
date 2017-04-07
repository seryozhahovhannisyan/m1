package branch;

import com.connectto.wallet.merchant.business.merchant.ICompanyManager;
import com.connectto.wallet.merchant.common.data.merchant.Company;
import com.connectto.wallet.merchant.common.exception.EntityNotFoundException;
import com.connectto.wallet.merchant.common.exception.InternalErrorException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Serozh on 3/14/2017.
 */
public class ManagerTest {
    public static void main(String[] args) throws EntityNotFoundException, InternalErrorException {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        ICompanyManager companyManager = (ICompanyManager)context.getBean("CompanyManagerImpl");
        Company company = companyManager.getById(1l);
        System.out.println(company);

    }
}
