import com.connectto.wallet.merchant.business.wallet.IWalletManager;
import com.connectto.wallet.merchant.web.action.BaseAction;
import com.connectto.wallet.merchant.web.action.dto.ResponseDto;
import com.connectto.wallet.merchant.web.action.dto.ResponseStatus;

/**
 * Created by Serozh on 9/26/2016.
 */
public class TransactionActionTest extends BaseAction {

    private ResponseDto dto;
    private IWalletManager walletManager;

    public static void main(String[] args) {
        System.out.println(111111);
    }

    public String withdrawStart() {
        /*dto.addResponse("data", wallets);
        dto.addResponse("dataCount", dataCount);*/
        dto.setResponseStatus(ResponseStatus.SUCCESS);
        return SUCCESS;
    }
    public String withdrawCheckTax() {
        /*dto.addResponse("data", wallets);
        dto.addResponse("dataCount", dataCount);*/
        dto.setResponseStatus(ResponseStatus.SUCCESS);
        return SUCCESS;
    }
    public String withdrawPreview() {
        dto.setResponseStatus(ResponseStatus.SUCCESS);
        return SUCCESS;
    }
    public String withdrawMakePayment() {
        dto.setResponseStatus(ResponseStatus.SUCCESS);
        return SUCCESS;
    }
    public String withdrawApprove() {
        dto.setResponseStatus(ResponseStatus.SUCCESS);
        return SUCCESS;
    }
    public String withdrawReject() {
        dto.setResponseStatus(ResponseStatus.SUCCESS);
        return SUCCESS;
    }
    
    public String depositStart() {
        /*dto.addResponse("data", wallets);
        dto.addResponse("dataCount", dataCount);*/
        //dto.addResponse("currencies", CurrencyType.values());
        dto.setResponseStatus(ResponseStatus.SUCCESS);
        return SUCCESS;
    }
    public String depositCheckTax() {
        /*dto.addResponse("data", wallets);
        dto.addResponse("dataCount", dataCount);*/
        dto.setResponseStatus(ResponseStatus.SUCCESS);
        return SUCCESS;
    }
    public String depositPreview() {
        dto.setResponseStatus(ResponseStatus.SUCCESS);
        return SUCCESS;
    }
    public String depositMakePayment() {
        dto.setResponseStatus(ResponseStatus.SUCCESS);
        return SUCCESS;
    }
    public String depositApprove() {
        dto.setResponseStatus(ResponseStatus.SUCCESS);
        return SUCCESS;
    }
    public String depositReject() {
        dto.setResponseStatus(ResponseStatus.SUCCESS);
        return SUCCESS;
    }
    

    public ResponseDto getDto() {
        return dto;
    }

    public void setDto(ResponseDto dto) {
        this.dto = dto;
    }

    public void setWalletManager(IWalletManager walletManager) {
        this.walletManager = walletManager;
    }
}
