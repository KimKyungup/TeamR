package com.samsung.hackerton18.teamr.belive.fragment.smartContract.contracts_class;

import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple4;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

import rx.Observable;
import rx.functions.Func1;


/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.3.1.
 */
public class TTS_Contract extends Contract {
    private static final String BINARY = "60606040526004361061008e576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806305af123714610093578063085d4883146100f057806318b0c4e4146101455780632fa0126b1461016857806376f75e7f146102045780638da5cb5b146102e5578063c3710f251461033a578063cfd8d6c014610363575b600080fd5b341561009e57600080fd5b6100ee600480803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509190505061039c565b005b34156100fb57600080fd5b6101036107a7565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b341561015057600080fd5b61016660048080359060200190919050506107cd565b005b341561017357600080fd5b6101896004808035906020019091905050610809565b6040518080602001828103825283818151815260200191508051906020019080838360005b838110156101c95780820151818401526020810190506101ae565b50505050905090810190601f1680156101f65780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b341561020f57600080fd5b6102256004808035906020019091905050610831565b604051808573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018060200184151515158152602001838152602001828103825285818151815260200191508051906020019080838360005b838110156102a757808201518184015260208101905061028c565b50505050905090810190601f1680156102d45780820380516001836020036101000a031916815260200191505b509550505050505060405180910390f35b34156102f057600080fd5b6102f8610935565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b341561034557600080fd5b61034d61095a565b6040518082815260200191505060405180910390f35b341561036e57600080fd5b61039a600480803573ffffffffffffffffffffffffffffffffffffffff16906020019091905050610a5a565b005b60008090505b6002805490508110156105cd57600115156002828154811015156103c257fe5b906000526020600020906004020160020160009054906101000a900460ff16151514156105c05760006002828154811015156103fa57fe5b906000526020600020906004020160020160006101000a81548160ff0219169083151502179055503360028281548110151561043257fe5b906000526020600020906004020160000160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055508160028281548110151561049157fe5b906000526020600020906004020160010190805190602001906104b5929190610a9e565b50436002828154811015156104c657fe5b9060005260206000209060040201600301819055507fa815610f90fdf55da2266d32b7560a19c774a26da4f7676afc93d3db622509798160028381548110151561050c57fe5b906000526020600020906004020160010160405180838152602001806020018281038252838181546001816001161561010002031660029004815260200191508054600181600116156101000203166002900480156105ac5780601f10610581576101008083540402835291602001916105ac565b820191906000526020600020905b81548152906001019060200180831161058f57829003601f168201915b5050935050505060405180910390a16107a3565b80806001019150506103a2565b600280548060010182816105e19190610b1e565b916000526020600020906004020160006080604051908101604052803373ffffffffffffffffffffffffffffffffffffffff16815260200186815260200160001515815260200143815250909190915060008201518160000160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506020820151816001019080519060200190610694929190610b50565b5060408201518160020160006101000a81548160ff021916908315150217905550606082015181600301555050507fa815610f90fdf55da2266d32b7560a19c774a26da4f7676afc93d3db62250979816002838154811015156106f357fe5b906000526020600020906004020160010160405180838152602001806020018281038252838181546001816001161561010002031660029004815260200191508054600181600116156101000203166002900480156107935780601f1061076857610100808354040283529160200191610793565b820191906000526020600020905b81548152906001019060200180831161077657829003601f168201915b5050935050505060405180910390a15b5050565b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b60016002828154811015156107de57fe5b906000526020600020906004020160020160006101000a81548160ff02191690831515021790555050565b610811610bd0565b60028281548110151561082057fe5b906000526020600020905050919050565b60028181548110151561084057fe5b90600052602060002090600402016000915090508060000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1690806001018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156109125780601f106108e757610100808354040283529160200191610912565b820191906000526020600020905b8154815290600101906020018083116108f557829003601f168201915b5050505050908060020160009054906101000a900460ff16908060030154905084565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b6000806000807fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff91507fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff9050600092505b600280549050831015610a5157600015156002848154811015156109cb57fe5b906000526020600020906004020160020160009054906101000a900460ff161515148015610a18575080600284815481101515610a0457fe5b906000526020600020906004020160030154105b15610a4457829150600282815481101515610a2f57fe5b90600052602060002090600402016003015490505b82806001019350506109ab565b81935050505090565b80600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10610adf57805160ff1916838001178555610b0d565b82800160010185558215610b0d579182015b82811115610b0c578251825591602001919060010190610af1565b5b509050610b1a9190610be4565b5090565b815481835581811511610b4b57600402816004028360005260206000209182019101610b4a9190610c09565b5b505050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10610b9157805160ff1916838001178555610bbf565b82800160010185558215610bbf579182015b82811115610bbe578251825591602001919060010190610ba3565b5b509050610bcc9190610be4565b5090565b602060405190810160405280600081525090565b610c0691905b80821115610c02576000816000905550600101610bea565b5090565b90565b610c7891905b80821115610c7457600080820160006101000a81549073ffffffffffffffffffffffffffffffffffffffff0219169055600182016000610c4f9190610c7b565b6002820160006101000a81549060ff0219169055600382016000905550600401610c0f565b5090565b90565b50805460018160011615610100020316600290046000825580601f10610ca15750610cc0565b601f016020900490600052602060002090810190610cbf9190610be4565b5b505600a165627a7a72305820cf5b52ec061c84cb0fd80c2cbabecab7061b1dc2dcb5911d7f25c106ebf94ffe0029";

    protected TTS_Contract(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected TTS_Contract(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public List<SayEventResponse> getSayEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("say",
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}));
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(event, transactionReceipt);
        ArrayList<SayEventResponse> responses = new ArrayList<SayEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            SayEventResponse typedResponse = new SayEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.orderID = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.content = (String) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<SayEventResponse> sayEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("say",
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, SayEventResponse>() {
            @Override
            public SayEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(event, log);
                SayEventResponse typedResponse = new SayEventResponse();
                typedResponse.log = log;
                typedResponse.orderID = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.content = (String) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public RemoteCall<TransactionReceipt> setNewOrder(String _content) {
        final Function function = new Function(
                "setNewOrder",
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_content)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<String> provider() {
        final Function function = new Function("provider",
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> setOrderComplete(BigInteger _orderID) {
        final Function function = new Function(
                "setOrderComplete",
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_orderID)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> getOrderContent(BigInteger _orderID) {
        final Function function = new Function(
                "getOrderContent",
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_orderID)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Tuple4<String, String, Boolean, BigInteger>> orderList(BigInteger param0) {
        final Function function = new Function("orderList",
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Bool>() {}, new TypeReference<Uint256>() {}));
        return new RemoteCall<Tuple4<String, String, Boolean, BigInteger>>(
                new Callable<Tuple4<String, String, Boolean, BigInteger>>() {
                    @Override
                    public Tuple4<String, String, Boolean, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple4<String, String, Boolean, BigInteger>(
                                (String) results.get(0).getValue(),
                                (String) results.get(1).getValue(),
                                (Boolean) results.get(2).getValue(),
                                (BigInteger) results.get(3).getValue());
                    }
                });
    }

    public RemoteCall<String> owner() {
        final Function function = new Function("owner",
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<BigInteger> getOrderID() {
        final Function function = new Function("getOrderID",
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> setProvider(String _provider) {
        final Function function = new Function(
                "setProvider",
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_provider)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public static RemoteCall<TTS_Contract> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(TTS_Contract.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<TTS_Contract> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(TTS_Contract.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static TTS_Contract load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new TTS_Contract(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static TTS_Contract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new TTS_Contract(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static class SayEventResponse {
        public Log log;

        public BigInteger orderID;

        public String content;
    }
}
