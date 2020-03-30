package org.fisco.bcos.channel.test.contract;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import org.fisco.bcos.channel.event.filter.EventLogPushWithDecodeCallback;
import org.fisco.bcos.web3j.abi.EventEncoder;
import org.fisco.bcos.web3j.abi.FunctionEncoder;
import org.fisco.bcos.web3j.abi.TypeReference;
import org.fisco.bcos.web3j.abi.datatypes.Event;
import org.fisco.bcos.web3j.abi.datatypes.Function;
import org.fisco.bcos.web3j.abi.datatypes.Type;
import org.fisco.bcos.web3j.abi.datatypes.Utf8String;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.RemoteCall;
import org.fisco.bcos.web3j.protocol.core.methods.response.Log;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tuples.generated.Tuple3;
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
public class Evidence extends Contract {
    public static final String[] BINARY_ARRAY = {"608060405234801561001057600080fd5b506040516107bf3803806107bf833981018060405281019080805182019291906020018051820192919060200180518201929190505050826000908051906020019061005d92919061020a565b50816001908051906020019061007492919061020a565b50806002908051906020019061008b92919061020a565b507faf1f4f8d84431b65de566a0a4f73763572c14edb25a1360312ff3c7b5386191183838360405180806020018060200180602001848103845287818151815260200191508051906020019080838360005b838110156100f85780820151818401526020810190506100dd565b50505050905090810190601f1680156101255780820380516001836020036101000a031916815260200191505b50848103835286818151815260200191508051906020019080838360005b8381101561015e578082015181840152602081019050610143565b50505050905090810190601f16801561018b5780820380516001836020036101000a031916815260200191505b50848103825285818151815260200191508051906020019080838360005b838110156101c45780820151818401526020810190506101a9565b50505050905090810190601f1680156101f15780820380516001836020036101000a031916815260200191505b50965050505050505060405180910390a15050506102af565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061024b57805160ff1916838001178555610279565b82800160010185558215610279579182015b8281111561027857825182559160200191906001019061025d565b5b509050610286919061028a565b5090565b6102ac91905b808211156102a8576000816000905550600101610290565b5090565b90565b610501806102be6000396000f30060806040526004361061004c576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff168063596f21f814610051578063c7eaf9b4146101b9575b600080fd5b34801561005d57600080fd5b50610066610249565b60405180806020018060200180602001848103845287818151815260200191508051906020019080838360005b838110156100ae578082015181840152602081019050610093565b50505050905090810190601f1680156100db5780820380516001836020036101000a031916815260200191505b50848103835286818151815260200191508051906020019080838360005b838110156101145780820151818401526020810190506100f9565b50505050905090810190601f1680156101415780820380516001836020036101000a031916815260200191505b50848103825285818151815260200191508051906020019080838360005b8381101561017a57808201518184015260208101905061015f565b50505050905090810190601f1680156101a75780820380516001836020036101000a031916815260200191505b50965050505050505060405180910390f35b3480156101c557600080fd5b506101ce610433565b6040518080602001828103825283818151815260200191508051906020019080838360005b8381101561020e5780820151818401526020810190506101f3565b50505050905090810190601f16801561023b5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b6060806060600060016002828054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156102e95780601f106102be576101008083540402835291602001916102e9565b820191906000526020600020905b8154815290600101906020018083116102cc57829003601f168201915b50505050509250818054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156103855780601f1061035a57610100808354040283529160200191610385565b820191906000526020600020905b81548152906001019060200180831161036857829003601f168201915b50505050509150808054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156104215780601f106103f657610100808354040283529160200191610421565b820191906000526020600020905b81548152906001019060200180831161040457829003601f168201915b50505050509050925092509250909192565b606060018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156104cb5780601f106104a0576101008083540402835291602001916104cb565b820191906000526020600020905b8154815290600101906020018083116104ae57829003601f168201915b50505050509050905600a165627a7a7230582077eb60b0e15e2d071e584d13dae4ba44e8c1bd2bb458dc2ad06f1bee06f4bdf00029"};

    public static final String BINARY = String.join("", BINARY_ARRAY);

    public static final String[] ABI_ARRAY = {"[{\"constant\":true,\"inputs\":[],\"name\":\"getEvidence\",\"outputs\":[{\"name\":\"\",\"type\":\"string\"},{\"name\":\"\",\"type\":\"string\"},{\"name\":\"\",\"type\":\"string\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"getEvidenceInfo\",\"outputs\":[{\"name\":\"\",\"type\":\"string\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"name\":\"evi\",\"type\":\"string\"},{\"name\":\"info\",\"type\":\"string\"},{\"name\":\"id\",\"type\":\"string\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"evi\",\"type\":\"string\"},{\"indexed\":false,\"name\":\"info\",\"type\":\"string\"},{\"indexed\":false,\"name\":\"id\",\"type\":\"string\"}],\"name\":\"newEvidenceEvent\",\"type\":\"event\"}]"};

    public static final String ABI = String.join("", ABI_ARRAY);

    public static final TransactionDecoder transactionDecoder = new TransactionDecoder(ABI, BINARY);

    public static final String FUNC_GETEVIDENCE = "getEvidence";

    public static final String FUNC_GETEVIDENCEINFO = "getEvidenceInfo";

    public static final Event NEWEVIDENCEEVENT_EVENT = new Event("newEvidenceEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
    ;

    @Deprecated
    protected Evidence(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Evidence(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Evidence(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Evidence(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static TransactionDecoder getTransactionDecoder() {
        return transactionDecoder;
    }

    public RemoteCall<Tuple3<String, String, String>> getEvidence() {
        final Function function = new Function(FUNC_GETEVIDENCE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
        return new RemoteCall<Tuple3<String, String, String>>(
                new Callable<Tuple3<String, String, String>>() {
                    @Override
                    public Tuple3<String, String, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple3<String, String, String>(
                                (String) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (String) results.get(2).getValue());
                    }
                });
    }

    public RemoteCall<String> getEvidenceInfo() {
        final Function function = new Function(FUNC_GETEVIDENCEINFO, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public List<NewEvidenceEventEventResponse> getNewEvidenceEventEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(NEWEVIDENCEEVENT_EVENT, transactionReceipt);
        ArrayList<NewEvidenceEventEventResponse> responses = new ArrayList<NewEvidenceEventEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            NewEvidenceEventEventResponse typedResponse = new NewEvidenceEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.evi = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.info = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.id = (String) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void registernewEvidenceEventEventLogFilter(String fromBlock, String toBlock, List<String> otherTopcs, EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(NEWEVIDENCEEVENT_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,fromBlock,toBlock,otherTopcs,callback);
    }

    public void registernewEvidenceEventEventLogFilter(EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(NEWEVIDENCEEVENT_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,callback);
    }

    @Deprecated
    public static Evidence load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Evidence(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Evidence load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Evidence(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Evidence load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Evidence(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Evidence load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Evidence(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Evidence> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String evi, String info, String id) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(evi), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(info), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(id)));
        return deployRemoteCall(Evidence.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<Evidence> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String evi, String info, String id) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(evi), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(info), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(id)));
        return deployRemoteCall(Evidence.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Evidence> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String evi, String info, String id) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(evi), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(info), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(id)));
        return deployRemoteCall(Evidence.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Evidence> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String evi, String info, String id) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(evi), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(info), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(id)));
        return deployRemoteCall(Evidence.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static class NewEvidenceEventEventResponse {
        public Log log;

        public String evi;

        public String info;

        public String id;
    }
}
