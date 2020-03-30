package org.fisco.bcos.channel.test.contract;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.fisco.bcos.channel.client.TransactionSucCallback;
import org.fisco.bcos.channel.event.filter.EventLogPushWithDecodeCallback;
import org.fisco.bcos.web3j.abi.EventEncoder;
import org.fisco.bcos.web3j.abi.FunctionReturnDecoder;
import org.fisco.bcos.web3j.abi.TypeReference;
import org.fisco.bcos.web3j.abi.datatypes.Event;
import org.fisco.bcos.web3j.abi.datatypes.Function;
import org.fisco.bcos.web3j.abi.datatypes.Type;
import org.fisco.bcos.web3j.abi.datatypes.generated.Uint256;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.RemoteCall;
import org.fisco.bcos.web3j.protocol.core.methods.response.Log;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tuples.generated.Tuple1;
import org.fisco.bcos.web3j.tx.Contract;
import org.fisco.bcos.web3j.tx.TransactionManager;
import org.fisco.bcos.web3j.tx.gas.ContractGasProvider;
import org.fisco.bcos.web3j.tx.txdecode.TransactionDecoder;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.fisco.bcos.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version none.
 */
@SuppressWarnings("unchecked")
public class QuickSort extends Contract {
    public static final String[] BINARY_ARRAY = {"608060405234801561001057600080fd5b506102d2806100206000396000f300608060405260043610610041576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff168063fe91386514610046575b600080fd5b34801561005257600080fd5b5061007160048036038101908080359060200190929190505050610073565b005b60606000826040519080825280602002602001820160405280156100a65781602001602082028038833980820191505090505b509150600090505b81518110156100e35780830382828151811015156100c857fe5b906020019060200201818152505080806001019150506100ae565b6100f3826000600185510361012f565b7fd353a1cbe5143a1ec767ce453eee65d823145f88787ff81f4260c5babc5bf807836040518082815260200191505060405180910390a1505050565b600080600080859350849250828414156101485761029d565b86600287870381151561015757fe5b04870181518110151561016657fe5b9060200190602002015191505b8284111515610262575b81878581518110151561018c57fe5b9060200190602002015110156101a957838060010194505061017d565b5b86838151811015156101b857fe5b906020019060200201518210156101d7578280600190039350506101aa565b828411151561025d5786848151811015156101ee57fe5b906020019060200201519050868381518110151561020857fe5b90602001906020020151878581518110151561022057fe5b906020019060200201818152505080878481518110151561023d57fe5b906020019060200201818152505083806001019450508280600190039350505b610173565b82861080156102715750600184115b156102855761028487876001870361012f565b5b8484101561029c5761029b87600186018761012f565b5b5b505050505050505600a165627a7a72305820b96252ab9d6a684abde4427f575728089f372eb29c4d23216fe339e9c4881b980029"};

    public static final String BINARY = String.join("", BINARY_ARRAY);

    public static final String[] ABI_ARRAY = {"[{\"constant\":false,\"inputs\":[{\"name\":\"size\",\"type\":\"uint256\"}],\"name\":\"sort\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"size\",\"type\":\"uint256\"}],\"name\":\"finish\",\"type\":\"event\"}]"};

    public static final String ABI = String.join("", ABI_ARRAY);

    public static final TransactionDecoder transactionDecoder = new TransactionDecoder(ABI, BINARY);

    public static final String FUNC_SORT = "sort";

    public static final Event FINISH_EVENT = new Event("finish", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected QuickSort(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected QuickSort(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected QuickSort(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected QuickSort(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static TransactionDecoder getTransactionDecoder() {
        return transactionDecoder;
    }

    public RemoteCall<TransactionReceipt> sort(BigInteger size) {
        final Function function = new Function(
                FUNC_SORT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(size)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void sort(BigInteger size, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_SORT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(size)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String sortSeq(BigInteger size) {
        final Function function = new Function(
                FUNC_SORT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(size)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple1<BigInteger> getSortInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_SORT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple1<BigInteger>(

                (BigInteger) results.get(0).getValue()
                );
    }

    public List<FinishEventResponse> getFinishEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(FINISH_EVENT, transactionReceipt);
        ArrayList<FinishEventResponse> responses = new ArrayList<FinishEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            FinishEventResponse typedResponse = new FinishEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.size = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void registerfinishEventLogFilter(String fromBlock, String toBlock, List<String> otherTopcs, EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(FINISH_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,fromBlock,toBlock,otherTopcs,callback);
    }

    public void registerfinishEventLogFilter(EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(FINISH_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,callback);
    }

    @Deprecated
    public static QuickSort load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new QuickSort(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static QuickSort load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new QuickSort(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static QuickSort load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new QuickSort(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static QuickSort load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new QuickSort(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<QuickSort> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(QuickSort.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<QuickSort> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(QuickSort.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<QuickSort> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(QuickSort.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<QuickSort> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(QuickSort.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class FinishEventResponse {
        public Log log;

        public BigInteger size;
    }
}
