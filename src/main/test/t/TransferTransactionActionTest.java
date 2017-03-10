package t;

import com.connectto.wallet.encryption.EncryptException;
import com.connectto.wallet.encryption.WalletEncription;

public class TransferTransactionActionTest {

    public static void main(String[] args) throws EncryptException {

        String _1000_ = WalletEncription.encrypt("1000");
        String _152_ = WalletEncription.encrypt("152");

        System.out.println("Hello TransferTransactionActionTest");
        System.out.println("_1000_" + _1000_);
        System.out.println("_152_" + _152_);

        String url = "http://127.0.0.1:8787/transfer-from-merchant-to-user.htm?currencyType=1444444444040454D444444444F4F454AAAAAAAAAAFAFAAA2DDDDDDDDD0D0DBDCFFFFFFFFFFFFF3F0DDDDDDDDD0D0D3DBFFFFFFFFFFFFFFF6EEEEEEEEE0E0EBE29999999990909A9499999999909092939999999990909E99444444444F4F454266666666606069633333333330303C3B999999999F9F9E90CCCCCCCCC0C0C3C&amount=1666666666060656BDDDDDDDDDFDFDFD9555555555F5F5550666666666060616B888888888F8F8E8F444444444F4F4D4266666666606069637777777770707D710000000000000401999999999090969EDDDDDDDDDFDFDBD2777777777070797B888888888F8F8E829999999990909A93FFFFFFFFF0F0FFFB333333333F3F3C3&itemId=1&name=name&description=description&walletId=25&companyId=33&secretKey=n3cb1gnzak1ebtmjee0wbdufre4r4ml0lpwgeoa4velv5ee267hmy41n36zhm4hx&clientKey=w589oanxshn3zhv2l4l8h2gx4g60wi9lt1xosp1mmnfhrbj78iow8jyx64oqkwev";

    }

}