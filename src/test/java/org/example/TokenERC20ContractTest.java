
/*
 * SPDX-License-Identifier: Apache-2.0
 */
package org.example;


/*
 * @author Renjith
 * @created: 8 Jun 2021.
 * @description : Unit test the erc20 operations.
 * It uses ChaincodeStubNaiveImpl class to mock all the operations of ChaincodeStub.
 * 
 * 
 */

import org.hyperledger.fabric.contract.Context;

import org.hyperledger.fabric.contract.ClientIdentity;


import org.hyperledger.fabric.shim.ChaincodeStub;
import org.hyperledger.fabric.shim.ledger.CompositeKey;
import org.hyperledger.fabric.shim.ChaincodeException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.exmaple.TokenERC20Contract;

public class TokenERC20ContractTest {

    final private String balancePrefix = "balance";
    final private String nameKey = "name";
    final private String symbolKey = "symbol";
    final private String decimalsKey = "decimals";
    final private String totalSupplyKey = "totalSupply";
    
    /*
     * 
     * Unit test the token attibutes operations 
     */
    
    

    @Nested
    class InvokeQueryERC20TokenOPtionsTransaction {
    	
    	/*
    	 * 
    	 * Testing the tokenName function working if the data stored correctly
    	 */

        @Test
        public void whenTokenNameExists() {
            TokenERC20Contract contract = new TokenERC20Contract();
            Context ctx = mock(Context.class);
            ChaincodeStub stub = mock(ChaincodeStub.class);
            when(ctx.getStub()).thenReturn(stub);
            when(stub.getStringState(nameKey)).thenReturn("ARBTToken");

            String toknName = contract.tokenName(ctx);

            assertThat(toknName).isEqualTo("ARBTToken");

        }
        
        /*
    	 * 
    	 * Testing the tokenName function throws error if the data not found
    	 */

        @Test
        public void whenTokenNameDoesNotExist() {
            TokenERC20Contract contract = new TokenERC20Contract();
            Context ctx = mock(Context.class);
            ChaincodeStub stub = mock(ChaincodeStub.class);
            when(ctx.getStub()).thenReturn(stub);
            when(stub.getStringState(nameKey)).thenReturn("");

            Throwable thrown = catchThrowable(() -> {
                contract.tokenName(ctx);
            });

            assertThat(thrown).isInstanceOf(ChaincodeException.class).hasNoCause()
                    .hasMessage("Sorry ! Token name not found");
        }
          
        /*
    	 * 
    	 * Testing the tokenSymbol function working if the data stored correctly
    	 */
        
        @Test
        public void whenTokenSymbolExists() {
            TokenERC20Contract contract = new TokenERC20Contract();
            Context ctx = mock(Context.class);
            ChaincodeStub stub = mock(ChaincodeStub.class);
            when(ctx.getStub()).thenReturn(stub);
            when(stub.getStringState(symbolKey)).thenReturn("ARBT");

            String toknName = contract.tokenSymbol(ctx);

            assertThat(toknName).isEqualTo("ARBT");

        }
        
        /*
    	 * 
    	 * Testing the tokenSymbol function throws error if the data not found
    	 */
        @Test
        public void whenTokenSymbolDoesNotExist() {
            TokenERC20Contract contract = new TokenERC20Contract();
            Context ctx = mock(Context.class);
            ChaincodeStub stub = mock(ChaincodeStub.class);
            when(ctx.getStub()).thenReturn(stub);
            when(stub.getStringState(symbolKey)).thenReturn("");

            Throwable thrown = catchThrowable(() -> {
                contract.tokenSymbol(ctx);
            });

            assertThat(thrown).isInstanceOf(ChaincodeException.class).hasNoCause()
                    .hasMessage("Sorry ! Token symbol not found");
        }
        
        
        /*
    	 * 
    	 * Testing the decimal function working if the data stored correctly
    	 */

        @Test
        public void whenTokenDecimalExists() {
            TokenERC20Contract contract = new TokenERC20Contract();
            Context ctx = mock(Context.class);
            ChaincodeStub stub = mock(ChaincodeStub.class);
            when(ctx.getStub()).thenReturn(stub);
            when(stub.getStringState(decimalsKey)).thenReturn("18");

            long decimal = contract.decimals(ctx);

            assertThat(decimal).isEqualTo(18);

        }
          
        /*
    	 * 
    	 * Testing the decimal function throws error if the data not found
    	 */
        @Test
        public void whenTokenDecimalNotExists() {
            TokenERC20Contract contract = new TokenERC20Contract();
            Context ctx = mock(Context.class);
            ChaincodeStub stub = mock(ChaincodeStub.class);
            when(ctx.getStub()).thenReturn(stub);
            when(stub.getStringState(decimalsKey)).thenReturn("");

            Throwable thrown = catchThrowable(() -> {
                contract.decimals(ctx);
            });

            assertThat(thrown).isInstanceOf(ChaincodeException.class).hasNoCause()
                    .hasMessage("Sorry ! Decimal not found");
        }
        
        
        /*
    	 * 
    	 * Testing the totlSupply function working if the data stored correctly
    	 */

        @Test
        public void whenTokenTotalSupplyExists() {
            TokenERC20Contract contract = new TokenERC20Contract();
            Context ctx = mock(Context.class);
            ChaincodeStub stub = mock(ChaincodeStub.class);
            when(ctx.getStub()).thenReturn(stub);
            when(stub.getStringState(totalSupplyKey)).thenReturn("222222222222");

            long totalSupply = contract.totalSupply(ctx);

            assertThat(totalSupply).isEqualTo(222222222222L);

        }

        
        /*
    	 * 
    	 * Testing the toalSupply function throws error if the data not found
    	 */
        @Test
        public void whenTokenTotalSupplyNotExists() {
            TokenERC20Contract contract = new TokenERC20Contract();
            Context ctx = mock(Context.class);
            ChaincodeStub stub = mock(ChaincodeStub.class);
            when(ctx.getStub()).thenReturn(stub);
            when(stub.getStringState(totalSupplyKey)).thenReturn("");

            Throwable thrown = catchThrowable(() -> {
                contract.totalSupply(ctx);
            });

            assertThat(thrown).isInstanceOf(ChaincodeException.class).hasNoCause()
                    .hasMessage("Sorry ! Total Supply  not found");
        }

        /*
    	 * 
    	 * Testing the client account id function working.
    	 * ChaincodeStubNaiveImpl is used to perform real operations on ChaincodeStub.
    	 */
        @Test
        public void whenClientAccountIDTest() throws Exception {

            TokenERC20Contract contract = new TokenERC20Contract();
            Context ctx = mock(Context.class);
            final ChaincodeStub stub = new ChaincodeStubNaiveImpl();
            final ClientIdentity identity = new ClientIdentity(stub);
            assertThat(identity.getMSPID()).isEqualTo("Org1MSP");
            when(ctx.getClientIdentity()).thenReturn(identity);
            String id = contract.getClientAccountID(ctx);
            String actualId = "x509::CN=admin, OU=Fabric, O=Hyperledger, ST=North Carolina, C=US::CN=example.com,"
                    + " OU=WWW, O=Internet Widgets, L=San Francisco, ST=California, C=US";
            assertThat(id).isEqualTo(actualId);

        }

    }
    
    /*
     * 
     * Unit test the token main function  operations 
     */  
    

    @Nested
    class TokenOperationsInvoke {
    	
    	
    	/*
    	 * Setting the symbols, name decimal etc.
    	 * 
    	 */

        @Test
        public void setOptionsTest() {
            TokenERC20Contract contract = new TokenERC20Contract();
            Context ctx = mock(Context.class);
            ChaincodeStub stub = mock(ChaincodeStub.class);
            when(ctx.getStub()).thenReturn(stub);
            contract.setOptions(ctx, "ARBTToken", "ARBT", "18");
            verify(stub).putStringState(nameKey, "ARBTToken");
            verify(stub).putStringState(symbolKey, "ARBT");
            verify(stub).putStringState(decimalsKey, "18");
        }
        
        
        /*
         * 
         * Test mint function with "Org1MSP"
         * Only Org1MSP memebers allowed to mint token.
         * The token is stored using certificated id  or client identity.
         */
        
        @Test
        public void whenOrgMintTokensTest() throws Exception {

            TokenERC20Contract contract = new TokenERC20Contract();
            Context ctx = mock(Context.class);
            final ChaincodeStub stub = new ChaincodeStubNaiveImpl();
            final ClientIdentity identity = new ClientIdentity(stub);
            when(ctx.getClientIdentity()).thenReturn(identity);

            when(ctx.getStub()).thenReturn(stub);
            boolean returnValue = contract.mint(ctx, "1000");
            assertThat(returnValue).isEqualTo(true);
            String totalSupply = stub.getStringState(totalSupplyKey.trim());
            assertThat(totalSupply).isEqualTo("1000");
            String minter = ctx.getClientIdentity().getId();
            CompositeKey balanceKey = stub.createCompositeKey(balancePrefix, minter);
            String updatedBalance = stub.getStringState(balanceKey.toString().trim());
            assertThat(updatedBalance).isEqualTo("1000");

        }
        
        /*
         * 
         * Test the trasnfer of the token from one account to another.
         * Firt mint the token then invoke transfer. Along with test total supply .balance etc.
         * So, Org1MSP member with client identity "x509::CN=admin, OU=Fabric, O=Hyperledger, ST=North Carolina, C=US::CN=example.com,"
                    + " OU=WWW, O=Internet Widgets, L=San Francisco, ST=California, C=US" transfer token to 
                    
                    "x509::CN=User1@org2.example.com, L=San Francisco, ST=California,"
                    + " C=US::CN=ca.org2.example.com, O=org2.example.com, L=San Francisco, ST=California, C=US";
                    
                   
         */
        
        

        @Test
        public void whenUserTransferTokenTest() throws Exception {

            TokenERC20Contract contract = new TokenERC20Contract();
            Context ctx = mock(Context.class);
            final ChaincodeStub stub = new ChaincodeStubNaiveImpl();
            final ClientIdentity identity = new ClientIdentity(stub);
            when(ctx.getClientIdentity()).thenReturn(identity);
            when(ctx.getStub()).thenReturn(stub);
            boolean returnValue = contract.mint(ctx, "1000");
            String minter = ctx.getClientIdentity().getId();
            String _to = "x509::CN=User1@org2.example.com, L=San Francisco, ST=California,"
                    + " C=US::CN=ca.org2.example.com, O=org2.example.com, L=San Francisco, ST=California, C=US";
            boolean transferResult = contract.transfer(ctx, _to, "100");
            CompositeKey toBalanceKey = stub.createCompositeKey(balancePrefix, _to);
            String _toCurrentBalance = stub.getStringState(toBalanceKey.toString().trim());
            Long totalSupply = contract.totalSupply(ctx);
            Long fromBalance = contract.balanceOf(ctx, minter);
            ((ChaincodeStubNaiveImpl) stub).setCertificate(ChaincodeStubNaiveImpl.CERT_WITH_DNS);
            Long _toBalance = contract.balanceOf(ctx, _to);

            assertThat(transferResult).isEqualTo(true);
            assertThat(returnValue).isEqualTo(true);
            assertThat(totalSupply).isEqualTo(1000);
            assertThat(_toCurrentBalance).isEqualTo("100");
            assertThat(fromBalance).isEqualTo(900);
            assertThat(_toBalance).isEqualTo(100);

        }
        
        /*
         * Test the burn  functionality.
         * 
         * * Firt mint the token then burn the smae user token
         * along with test totalsupply .balance etc.
         * 
         */

        @Test
        public void whenOrgBurnsTokenTest() throws Exception {

            TokenERC20Contract contract = new TokenERC20Contract();
            Context ctx = mock(Context.class);
            final ChaincodeStub stub = new ChaincodeStubNaiveImpl();
            final ClientIdentity identity = new ClientIdentity(stub);
            when(ctx.getClientIdentity()).thenReturn(identity);
            when(ctx.getStub()).thenReturn(stub);
            boolean returnValue = contract.mint(ctx, "1000");
            String minter = ctx.getClientIdentity().getId();
            boolean burnResult = contract.burn(ctx, "100");
            Long totalSupply = contract.totalSupply(ctx);
            Long fromBalance = contract.balanceOf(ctx, minter);
            assertThat(returnValue).isEqualTo(true);
            assertThat(burnResult).isEqualTo(true);
            assertThat(totalSupply).isEqualTo(900);
            assertThat(fromBalance).isEqualTo(900);

        }

    }
    
    
    /*
     * 
     * Unit test the erc20 token additional functionalities like  request approval for transfer and trasferFrom operations 
     */
    @Nested
    class InvokeERC20AllowanceTransactions {

        private Context ctx = null;
        private ChaincodeStub stub = null;
        private ClientIdentity identity = null;
        private TokenERC20Contract contract = null;

        @BeforeEach
        public void initialize() {
            try {

                this.ctx = mock(Context.class);
                this.stub = new ChaincodeStubNaiveImpl();
                this.identity = new ClientIdentity(stub);
                when(ctx.getClientIdentity()).thenReturn(identity);
                when(ctx.getStub()).thenReturn(stub);
                contract = new TokenERC20Contract();
                contract.mint(ctx, "1000");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        /*
         * 
         * Unit test the erc20 token approve permission for transferring token from the owner account  
         */
        @Test
        public void approveForTokenAllowanceTest() {

            String spender = "x509::CN=User1@org2.example.com, L=San Francisco, ST=California,"
                    + " C=US::CN=ca.org2.example.com, O=org2.example.com, L=San Francisco, ST=California, C=US";
            boolean result = contract.approve(ctx, spender, "200");
            assertThat(result).isEqualTo(true);
            String owner = ctx.getClientIdentity().getId();
            long allowance = contract.allowance(ctx, owner, spender);
            assertThat(allowance).isEqualTo(200);

        }

        /*
         * 
         * Unit test the erc20 token transferFrom operation. 
         */
        @Test
        public void allowanceTransferFromTest() throws Exception {

            /*
             * ChaincodeStub localStub = new ChaincodeStubNaiveImpl();
             * ((ChaincodeStubNaiveImpl)
             * localStub).setCertificate(ChaincodeStubNaiveImpl.CERT_WITH_DNS); Context
             * localCtx = mock(Context.class); ClientIdentity localidentity = new
             * ClientIdentity(localStub);
             * when(localCtx.getClientIdentity()).thenReturn(localidentity);
             * when(localCtx.getStub()).thenReturn(localStub);
             */

            String spender = "x509::CN=User1@org2.example.com, L=San Francisco, ST=California,"
                    + " C=US::CN=ca.org2.example.com, O=org2.example.com, L=San Francisco, ST=California, C=US";
            String to = "x509::CN=User2@org2.example.com, L=San Francisco, ST=California,"
                    + " C=US::CN=ca.org2.example.com, O=org2.example.com, L=San Francisco, ST=California, C=US";
            boolean result = contract.approve(ctx, spender, "200");
            String owner = ctx.getClientIdentity().getId();

            ((ChaincodeStubNaiveImpl) stub).setCertificate(ChaincodeStubNaiveImpl.CERT_WITH_DNS);
            identity = new ClientIdentity(stub);
            when(ctx.getClientIdentity()).thenReturn(identity);
            when(ctx.getStub()).thenReturn(stub);
            boolean transferResult = contract.transferFrom(ctx, owner, to, "100");
            long allowance = contract.allowance(ctx, owner, spender);

            assertThat(result).isEqualTo(true);
            assertThat(transferResult).isEqualTo(true);
            assertThat(allowance).isEqualTo(100);

        }

    }

}
