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
import org.fisco.bcos.web3j.abi.datatypes.Address;
import org.fisco.bcos.web3j.abi.datatypes.Bool;
import org.fisco.bcos.web3j.abi.datatypes.DynamicArray;
import org.fisco.bcos.web3j.abi.datatypes.DynamicBytes;
import org.fisco.bcos.web3j.abi.datatypes.Event;
import org.fisco.bcos.web3j.abi.datatypes.Function;
import org.fisco.bcos.web3j.abi.datatypes.Type;
import org.fisco.bcos.web3j.abi.datatypes.Utf8String;
import org.fisco.bcos.web3j.abi.datatypes.generated.Uint256;
import org.fisco.bcos.web3j.abi.datatypes.generated.Uint8;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.RemoteCall;
import org.fisco.bcos.web3j.protocol.core.methods.response.Log;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tuples.generated.Tuple1;
import org.fisco.bcos.web3j.tuples.generated.Tuple2;
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
public class FTN extends Contract {
    public static final String[] BINARY_ARRAY = {"60806040526001600360006101000a81548160ff021916908315150217905550732f22dc7ea406b14ec368c2d4875946adfd02450e600960006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055503480156200008157600080fd5b50336000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506001600260007007a394b99baffd858ce77a56ca11e9375773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060006101000a81548160ff02191690831515021790555060016002600073a0ae338e9fc22de613cec2d79477877f02751ceb73ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060006101000a81548160ff02191690831515021790555060016002600073721ea19d5e96eeb25c6e847f3209f3ca82b41cc973ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060006101000a81548160ff0219169083151502179055506040805190810160405280600381526020017f46544e00000000000000000000000000000000000000000000000000000000008152506004908051906020019062000250929190620003d4565b506040805190810160405280600581526020017f464f544f4e000000000000000000000000000000000000000000000000000000815250600590805190602001906200029e929190620003d4565b506012600660006101000a81548160ff021916908360ff160217905550600660009054906101000a900460ff1660ff16600a0a64012a05f20002600781905550600754600a60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020819055506000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16600073ffffffffffffffffffffffffffffffffffffffff167fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef6007546040518082815260200191505060405180910390a362000483565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106200041757805160ff191683800117855562000448565b8280016001018555821562000448579182015b82811115620004475782518255916020019190600101906200042a565b5b5090506200045791906200045b565b5090565b6200048091905b808211156200047c57600081600090555060010162000462565b5090565b90565b61243c80620004936000396000f300608060405260043610610154576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806306fdde0314610251578063095ea7b3146102e157806318160ddd1461034657806323b872dd14610371578063272deb73146103f65780632d07b28e1461044d578063313ce5671461047857806349721832146104a95780636d1b229d146104d457806370a082311461051957806371098a351461057057806375c7c82e1461059f57806379ba5097146105f65780638b7afe2e1461060d5780638da5cb5b1461063857806395d89b411461068f578063a9059cbb1461071f578063aad41a4114610784578063c711003514610841578063cae9ca51146108a6578063d4ee1d9014610951578063d85bd526146109a8578063dc39d06d146109d7578063dd62ed3e14610a3c578063f2fde38b14610ab3578063f8685b6914610af6575b3373ffffffffffffffffffffffffffffffffffffffff167fe1c5e54d52bd7b4ce225cc28afbcd0d44c4aff0a689cc20f5f4ee1ee3d7e839c346040518082815260200191505060405180910390a2600960009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166108fc61020560646101f7600334610b5190919063ffffffff16565b610b8290919063ffffffff16565b9081150290604051600060405180830381858888f19350505050158015610230573d6000803e3d6000fd5b503073ffffffffffffffffffffffffffffffffffffffff1631600881905550005b34801561025d57600080fd5b50610266610ba6565b6040518080602001828103825283818151815260200191508051906020019080838360005b838110156102a657808201518184015260208101905061028b565b50505050905090810190601f1680156102d35780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b3480156102ed57600080fd5b5061032c600480360381019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190929190505050610c44565b604051808215151515815260200191505060405180910390f35b34801561035257600080fd5b5061035b610d51565b6040518082815260200191505060405180910390f35b34801561037d57600080fd5b506103dc600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190929190505050610dac565b604051808215151515815260200191505060405180910390f35b34801561040257600080fd5b5061040b61115b565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561045957600080fd5b50610462611181565b6040518082815260200191505060405180910390f35b34801561048457600080fd5b5061048d611187565b604051808260ff1660ff16815260200191505060405180910390f35b3480156104b557600080fd5b506104be61119a565b6040518082815260200191505060405180910390f35b3480156104e057600080fd5b506104ff600480360381019080803590602001909291905050506111a0565b604051808215151515815260200191505060405180910390f35b34801561052557600080fd5b5061055a600480360381019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919050505061131f565b6040518082815260200191505060405180910390f35b34801561057c57600080fd5b50610585611368565b604051808215151515815260200191505060405180910390f35b3480156105ab57600080fd5b506105b461141d565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561060257600080fd5b5061060b611443565b005b34801561061957600080fd5b506106226115e2565b6040518082815260200191505060405180910390f35b34801561064457600080fd5b5061064d6115e8565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561069b57600080fd5b506106a461160d565b6040518080602001828103825283818151815260200191508051906020019080838360005b838110156106e45780820151818401526020810190506106c9565b50505050905090810190601f1680156107115780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34801561072b57600080fd5b5061076a600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803590602001909291905050506116ab565b604051808215151515815260200191505060405180910390f35b34801561079057600080fd5b5061082b60048036038101908080359060200190820180359060200190808060200260200160405190810160405280939291908181526020018383602002808284378201915050505050509192919290803590602001908201803590602001908080602002602001604051908101604052809392919081815260200183836020028082843782019150505050505091929192905050506118bf565b6040518082815260200191505060405180910390f35b34801561084d57600080fd5b5061088c600480360381019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190929190505050611bbe565b604051808215151515815260200191505060405180910390f35b3480156108b257600080fd5b50610937600480360381019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190929190803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290505050611e8b565b604051808215151515815260200191505060405180910390f35b34801561095d57600080fd5b506109666120f5565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b3480156109b457600080fd5b506109bd61211b565b604051808215151515815260200191505060405180910390f35b3480156109e357600080fd5b50610a22600480360381019080803573ffffffffffffffffffffffffffffffffffffffff1690602001909291908035906020019092919050505061212e565b604051808215151515815260200191505060405180910390f35b348015610a4857600080fd5b50610a9d600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050612292565b6040518082815260200191505060405180910390f35b348015610abf57600080fd5b50610af4600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050612319565b005b348015610b0257600080fd5b50610b37600480360381019080803573ffffffffffffffffffffffffffffffffffffffff1690602001909291905050506123b8565b604051808215151515815260200191505060405180910390f35b600081830290506000831480610b715750818382811515610b6e57","fe5b04145b1515610b7c57600080fd5b92915050565b60008082111515610b9257600080fd5b8183811515610b9d57fe5b04905092915050565b60058054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610c3c5780601f10610c1157610100808354040283529160200191610c3c565b820191906000526020600020905b815481529060010190602001808311610c1f57829003601f168201915b505050505081565b6000600360009054906101000a900460ff161515610c6157600080fd5b81600b60003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020819055508273ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff167f8c5be1e5ebec7d5bd14f71427d1e84f3dd0314c0f7b2291e5b200ac8c7c3b925846040518082815260200191505060405180910390a36001905092915050565b6000610da7600a60008073ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020546007546123d890919063ffffffff16565b905090565b6000600360009054906101000a900460ff161515610dc957600080fd5b600a60008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020548211151515610e1757600080fd5b600b60008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020548211151515610ea257600080fd5b60008214151515610eb257600080fd5b610f0482600a60008773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020546123d890919063ffffffff16565b600a60008673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002081905550610fd682600b60008773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020546123d890919063ffffffff16565b600b60008673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020819055506110a882600a60008673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020546123f490919063ffffffff16565b600a60008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020819055508273ffffffffffffffffffffffffffffffffffffffff168473ffffffffffffffffffffffffffffffffffffffff167fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef846040518082815260200191505060405180910390a3600190509392505050565b600e60009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b600c5481565b600660009054906101000a900460ff1681565b600d5481565b6000600a60003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000205482111515156111f057600080fd5b6000821415151561120057600080fd5b61125282600a60003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020546123d890919063ffffffff16565b600a60003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020819055506112aa826007546123d890919063ffffffff16565b600781905550600073ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff167fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef846040518082815260200191505060405180910390a360019050919050565b6000600a60008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020549050919050565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff161415156113c557600080fd5b600360009054906101000a900460ff16156113fa576000600360006101000a81548160ff021916908315150217905550611416565b6001600360006101000a81548160ff0219169083151502179055505b6001905090565b600f60009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614151561149f57600080fd5b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff167f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e060405160405180910390a3600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff166000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506000600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550565b60085481565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b60048054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156116a35780601f10611678576101008083540402835291602001916116a3565b820191906000526020600020905b81548152906001019060200180831161168657829003601f168201915b505050505081565b6000600360009054906101000a900460ff1615156116c857600080fd5b600a60003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002054821115151561171657600080fd5b6000821415151561172657600080fd5b61177882600a60003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020546123d890919063ffffffff16565b600a60003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000208190555061180d82600a60008673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020546123f490919063ffffffff16565b600a60008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020819055508273ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff167fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef846040518082815260200191505060405180910390a36001905092915050565b6000806000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614151561191d57600080fd5b600090505b8351811015611bb4576119b5838281518110151561193c57fe5b90602001906020020151600a60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020546123d890919063ffffffff16565b600a60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002081905550611a998382815181101515611a2a57fe5b90602001906020020151600a60008785815181101515611a4657fe5b9060200190602002015173ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020546123f490919063ffffffff16565b600a60008684815181101515611aab57fe5b9060200190602002015173ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020819055508381815181101515611b0157fe5b9060200190602002015173ffffffffffffffffffffffffffffffffffffffff166000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff167fddf252ad1be2c89b69c2b068fc378daa952b","a7f163c4a11628f55a4df523b3ef8584815181101515611b8857fe5b906020019060200201516040518082815260200191505060405180910390a38080600101915050611922565b8091505092915050565b6000600260003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060009054906101000a900460ff161515611c1857600080fd5b6000600c54148015611c2c57506000600d54145b15611cda57611c46610e10426123f490919063ffffffff16565b600c8190555081600d8190555033600e60006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555082600f60006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550611e81565b600e60009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614158015611d855750600f60009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168373ffffffffffffffffffffffffffffffffffffffff16145b8015611d925750600d5482145b8015611d9f5750600c5442105b15611dec578273ffffffffffffffffffffffffffffffffffffffff166108fc839081150290604051600060405180830381858888f19350505050158015611dea573d6000803e3d6000fd5b505b6000600c819055506000600d819055506000600e60006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506000600f60006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505b6001905092915050565b6000600360009054906101000a900460ff161515611ea857600080fd5b82600b60003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020819055508373ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff167f8c5be1e5ebec7d5bd14f71427d1e84f3dd0314c0f7b2291e5b200ac8c7c3b925856040518082815260200191505060405180910390a38373ffffffffffffffffffffffffffffffffffffffff16638f4ffcb1338530866040518563ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018481526020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200180602001828103825283818151815260200191508051906020019080838360005b83811015612083578082015181840152602081019050612068565b50505050905090810190601f1680156120b05780820380516001836020036101000a031916815260200191505b5095505050505050600060405180830381600087803b1580156120d257600080fd5b505af11580156120e6573d6000803e3d6000fd5b50505050600190509392505050565b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b600360009054906101000a900460ff1681565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614151561218b57600080fd5b8273ffffffffffffffffffffffffffffffffffffffff1663a9059cbb6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff16846040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200182815260200192505050602060405180830381600087803b15801561224f57600080fd5b505af1158015612263573d6000803e3d6000fd5b505050506040513d602081101561227957600080fd5b8101908080519060200190929190505050905092915050565b6000600b60008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002054905092915050565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614151561237457600080fd5b80600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555050565b60026020528060005260406000206000915054906101000a900460ff1681565b60008282111515156123e957600080fd5b818303905092915050565b6000818301905082811015151561240a57600080fd5b929150505600a165627a7a72305820729e2d1cef8022846371ba0d512f2cb9947f12d030d96d0861a295ac6281314b0029"};

    public static final String BINARY = String.join("", BINARY_ARRAY);

    public static final String[] ABI_ARRAY = {"[{\"constant\":true,\"inputs\":[],\"name\":\"name\",\"outputs\":[{\"name\":\"\",\"type\":\"string\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"spender\",\"type\":\"address\"},{\"name\":\"tokens\",\"type\":\"uint256\"}],\"name\":\"approve\",\"outputs\":[{\"name\":\"success\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"totalSupply\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"from\",\"type\":\"address\"},{\"name\":\"to\",\"type\":\"address\"},{\"name\":\"tokens\",\"type\":\"uint256\"}],\"name\":\"transferFrom\",\"outputs\":[{\"name\":\"success\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"reqAddress\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"reqTime\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"decimals\",\"outputs\":[{\"name\":\"\",\"type\":\"uint8\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"reqAmount\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"tokens\",\"type\":\"uint256\"}],\"name\":\"burnTokens\",\"outputs\":[{\"name\":\"success\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"tokenOwner\",\"type\":\"address\"}],\"name\":\"balanceOf\",\"outputs\":[{\"name\":\"balance\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[],\"name\":\"startStop\",\"outputs\":[{\"name\":\"success\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"reqTo\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[],\"name\":\"acceptOwnership\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"contractBalance\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"owner\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"symbol\",\"outputs\":[{\"name\":\"\",\"type\":\"string\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"to\",\"type\":\"address\"},{\"name\":\"tokens\",\"type\":\"uint256\"}],\"name\":\"transfer\",\"outputs\":[{\"name\":\"success\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"to\",\"type\":\"address[]\"},{\"name\":\"values\",\"type\":\"uint256[]\"}],\"name\":\"multisend\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"to\",\"type\":\"address\"},{\"name\":\"amount\",\"type\":\"uint256\"}],\"name\":\"multiSigWithdrawal\",\"outputs\":[{\"name\":\"success\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"spender\",\"type\":\"address\"},{\"name\":\"tokens\",\"type\":\"uint256\"},{\"name\":\"data\",\"type\":\"bytes\"}],\"name\":\"approveAndCall\",\"outputs\":[{\"name\":\"success\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"newOwner\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"running\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"tokenAddress\",\"type\":\"address\"},{\"name\":\"tokens\",\"type\":\"uint256\"}],\"name\":\"transferAnyERC20Token\",\"outputs\":[{\"name\":\"success\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"tokenOwner\",\"type\":\"address\"},{\"name\":\"spender\",\"type\":\"address\"}],\"name\":\"allowance\",\"outputs\":[{\"name\":\"remaining\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_newOwner\",\"type\":\"address\"}],\"name\":\"transferOwnership\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"\",\"type\":\"address\"}],\"name\":\"isBenefeciary\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"},{\"payable\":true,\"stateMutability\":\"payable\",\"type\":\"fallback\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"name\":\"_from\",\"type\":\"address\"},{\"indexed\":true,\"name\":\"_to\",\"type\":\"address\"}],\"name\":\"OwnershipTransferred\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"name\":\"from\",\"type\":\"address\"},{\"indexed\":true,\"name\":\"to\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"tokens\",\"type\":\"uint256\"}],\"name\":\"Transfer\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"name\":\"tokenOwner\",\"type\":\"address\"},{\"indexed\":true,\"name\":\"spender\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"tokens\",\"type\":\"uint256\"}],\"name\":\"Approval\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"name\":\"from\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"coins\",\"type\":\"uint256\"}],\"name\":\"Funds\",\"type\":\"event\"}]"};

    public static final String ABI = String.join("", ABI_ARRAY);

    public static final TransactionDecoder transactionDecoder = new TransactionDecoder(ABI, BINARY);

    public static final String FUNC_NAME = "name";

    public static final String FUNC_APPROVE = "approve";

    public static final String FUNC_TOTALSUPPLY = "totalSupply";

    public static final String FUNC_TRANSFERFROM = "transferFrom";

    public static final String FUNC_REQADDRESS = "reqAddress";

    public static final String FUNC_REQTIME = "reqTime";

    public static final String FUNC_DECIMALS = "decimals";

    public static final String FUNC_REQAMOUNT = "reqAmount";

    public static final String FUNC_BURNTOKENS = "burnTokens";

    public static final String FUNC_BALANCEOF = "balanceOf";

    public static final String FUNC_STARTSTOP = "startStop";

    public static final String FUNC_REQTO = "reqTo";

    public static final String FUNC_ACCEPTOWNERSHIP = "acceptOwnership";

    public static final String FUNC_CONTRACTBALANCE = "contractBalance";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_SYMBOL = "symbol";

    public static final String FUNC_TRANSFER = "transfer";

    public static final String FUNC_MULTISEND = "multisend";

    public static final String FUNC_MULTISIGWITHDRAWAL = "multiSigWithdrawal";

    public static final String FUNC_APPROVEANDCALL = "approveAndCall";

    public static final String FUNC_NEWOWNER = "newOwner";

    public static final String FUNC_RUNNING = "running";

    public static final String FUNC_TRANSFERANYERC20TOKEN = "transferAnyERC20Token";

    public static final String FUNC_ALLOWANCE = "allowance";

    public static final String FUNC_TRANSFEROWNERSHIP = "transferOwnership";

    public static final String FUNC_ISBENEFECIARY = "isBenefeciary";

    public static final Event OWNERSHIPTRANSFERRED_EVENT = new Event("OwnershipTransferred", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event TRANSFER_EVENT = new Event("Transfer", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event APPROVAL_EVENT = new Event("Approval", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event FUNDS_EVENT = new Event("Funds", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected FTN(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected FTN(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected FTN(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected FTN(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static TransactionDecoder getTransactionDecoder() {
        return transactionDecoder;
    }

    public RemoteCall<String> name() {
        final Function function = new Function(FUNC_NAME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> approve(String spender, BigInteger tokens) {
        final Function function = new Function(
                FUNC_APPROVE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(spender), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(tokens)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void approve(String spender, BigInteger tokens, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_APPROVE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(spender), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(tokens)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String approveSeq(String spender, BigInteger tokens) {
        final Function function = new Function(
                FUNC_APPROVE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(spender), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(tokens)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple2<String, BigInteger> getApproveInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_APPROVE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple2<String, BigInteger>(

                (String) results.get(0).getValue(), 
                (BigInteger) results.get(1).getValue()
                );
    }

    public Tuple1<Boolean> getApproveOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_APPROVE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple1<Boolean>(

                (Boolean) results.get(0).getValue()
                );
    }

    public RemoteCall<BigInteger> totalSupply() {
        final Function function = new Function(FUNC_TOTALSUPPLY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> transferFrom(String from, String to, BigInteger tokens) {
        final Function function = new Function(
                FUNC_TRANSFERFROM, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(from), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(to), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(tokens)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void transferFrom(String from, String to, BigInteger tokens, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_TRANSFERFROM, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(from), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(to), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(tokens)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String transferFromSeq(String from, String to, BigInteger tokens) {
        final Function function = new Function(
                FUNC_TRANSFERFROM, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(from), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(to), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(tokens)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple3<String, String, BigInteger> getTransferFromInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_TRANSFERFROM, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple3<String, String, BigInteger>(

                (String) results.get(0).getValue(), 
                (String) results.get(1).getValue(), 
                (BigInteger) results.get(2).getValue()
                );
    }

    public Tuple1<Boolean> getTransferFromOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_TRANSFERFROM, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple1<Boolean>(

                (Boolean) results.get(0).getValue()
                );
    }

    public RemoteCall<String> reqAddress() {
        final Function function = new Function(FUNC_REQADDRESS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<BigInteger> reqTime() {
        final Function function = new Function(FUNC_REQTIME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> decimals() {
        final Function function = new Function(FUNC_DECIMALS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> reqAmount() {
        final Function function = new Function(FUNC_REQAMOUNT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> burnTokens(BigInteger tokens) {
        final Function function = new Function(
                FUNC_BURNTOKENS, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(tokens)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void burnTokens(BigInteger tokens, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_BURNTOKENS, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(tokens)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String burnTokensSeq(BigInteger tokens) {
        final Function function = new Function(
                FUNC_BURNTOKENS, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(tokens)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple1<BigInteger> getBurnTokensInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_BURNTOKENS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple1<BigInteger>(

                (BigInteger) results.get(0).getValue()
                );
    }

    public Tuple1<Boolean> getBurnTokensOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_BURNTOKENS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple1<Boolean>(

                (Boolean) results.get(0).getValue()
                );
    }

    public RemoteCall<BigInteger> balanceOf(String tokenOwner) {
        final Function function = new Function(FUNC_BALANCEOF, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(tokenOwner)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> startStop() {
        final Function function = new Function(
                FUNC_STARTSTOP, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void startStop(TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_STARTSTOP, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String startStopSeq() {
        final Function function = new Function(
                FUNC_STARTSTOP, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple1<Boolean> getStartStopOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_STARTSTOP, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple1<Boolean>(

                (Boolean) results.get(0).getValue()
                );
    }

    public RemoteCall<String> reqTo() {
        final Function function = new Function(FUNC_REQTO, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> acceptOwnership() {
        final Function function = new Function(
                FUNC_ACCEPTOWNERSHIP, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void acceptOwnership(TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_ACCEPTOWNERSHIP, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String acceptOwnershipSeq() {
        final Function function = new Function(
                FUNC_ACCEPTOWNERSHIP, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<BigInteger> contractBalance() {
        final Function function = new Function(FUNC_CONTRACTBALANCE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<String> owner() {
        final Function function = new Function(FUNC_OWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<String> symbol() {
        final Function function = new Function(FUNC_SYMBOL, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> transfer(String to, BigInteger tokens) {
        final Function function = new Function(
                FUNC_TRANSFER, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(to), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(tokens)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void transfer(String to, BigInteger tokens, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_TRANSFER, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(to), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(tokens)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String transferSeq(String to, BigInteger tokens) {
        final Function function = new Function(
                FUNC_TRANSFER, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(to), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(tokens)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple2<String, BigInteger> getTransferInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_TRANSFER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple2<String, BigInteger>(

                (String) results.get(0).getValue(), 
                (BigInteger) results.get(1).getValue()
                );
    }

    public Tuple1<Boolean> getTransferOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_TRANSFER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple1<Boolean>(

                (Boolean) results.get(0).getValue()
                );
    }

    public RemoteCall<TransactionReceipt> multisend(List<String> to, List<BigInteger> values) {
        final Function function = new Function(
                FUNC_MULTISEND, 
                Arrays.<Type>asList(to.isEmpty()?org.fisco.bcos.web3j.abi.datatypes.DynamicArray.empty("address[]"):new org.fisco.bcos.web3j.abi.datatypes.DynamicArray<org.fisco.bcos.web3j.abi.datatypes.Address>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(to, org.fisco.bcos.web3j.abi.datatypes.Address.class)), 
                values.isEmpty()?org.fisco.bcos.web3j.abi.datatypes.DynamicArray.empty("uint256[]"):new org.fisco.bcos.web3j.abi.datatypes.DynamicArray<org.fisco.bcos.web3j.abi.datatypes.generated.Uint256>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(values, org.fisco.bcos.web3j.abi.datatypes.generated.Uint256.class))), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void multisend(List<String> to, List<BigInteger> values, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_MULTISEND, 
                Arrays.<Type>asList(to.isEmpty()?org.fisco.bcos.web3j.abi.datatypes.DynamicArray.empty("address[]"):new org.fisco.bcos.web3j.abi.datatypes.DynamicArray<org.fisco.bcos.web3j.abi.datatypes.Address>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(to, org.fisco.bcos.web3j.abi.datatypes.Address.class)), 
                values.isEmpty()?org.fisco.bcos.web3j.abi.datatypes.DynamicArray.empty("uint256[]"):new org.fisco.bcos.web3j.abi.datatypes.DynamicArray<org.fisco.bcos.web3j.abi.datatypes.generated.Uint256>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(values, org.fisco.bcos.web3j.abi.datatypes.generated.Uint256.class))), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String multisendSeq(List<String> to, List<BigInteger> values) {
        final Function function = new Function(
                FUNC_MULTISEND, 
                Arrays.<Type>asList(to.isEmpty()?org.fisco.bcos.web3j.abi.datatypes.DynamicArray.empty("address[]"):new org.fisco.bcos.web3j.abi.datatypes.DynamicArray<org.fisco.bcos.web3j.abi.datatypes.Address>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(to, org.fisco.bcos.web3j.abi.datatypes.Address.class)), 
                values.isEmpty()?org.fisco.bcos.web3j.abi.datatypes.DynamicArray.empty("uint256[]"):new org.fisco.bcos.web3j.abi.datatypes.DynamicArray<org.fisco.bcos.web3j.abi.datatypes.generated.Uint256>(
                        org.fisco.bcos.web3j.abi.Utils.typeMap(values, org.fisco.bcos.web3j.abi.datatypes.generated.Uint256.class))), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple2<List<String>, List<BigInteger>> getMultisendInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_MULTISEND, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Address>>() {}, new TypeReference<DynamicArray<Uint256>>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple2<List<String>, List<BigInteger>>(

                convertToNative((List<Address>) results.get(0).getValue()), 
                convertToNative((List<Uint256>) results.get(1).getValue())
                );
    }

    public Tuple1<BigInteger> getMultisendOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_MULTISEND, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple1<BigInteger>(

                (BigInteger) results.get(0).getValue()
                );
    }

    public RemoteCall<TransactionReceipt> multiSigWithdrawal(String to, BigInteger amount) {
        final Function function = new Function(
                FUNC_MULTISIGWITHDRAWAL, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(to), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void multiSigWithdrawal(String to, BigInteger amount, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_MULTISIGWITHDRAWAL, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(to), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String multiSigWithdrawalSeq(String to, BigInteger amount) {
        final Function function = new Function(
                FUNC_MULTISIGWITHDRAWAL, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(to), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple2<String, BigInteger> getMultiSigWithdrawalInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_MULTISIGWITHDRAWAL, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple2<String, BigInteger>(

                (String) results.get(0).getValue(), 
                (BigInteger) results.get(1).getValue()
                );
    }

    public Tuple1<Boolean> getMultiSigWithdrawalOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_MULTISIGWITHDRAWAL, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple1<Boolean>(

                (Boolean) results.get(0).getValue()
                );
    }

    public RemoteCall<TransactionReceipt> approveAndCall(String spender, BigInteger tokens, byte[] data) {
        final Function function = new Function(
                FUNC_APPROVEANDCALL, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(spender), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(tokens), 
                new org.fisco.bcos.web3j.abi.datatypes.DynamicBytes(data)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void approveAndCall(String spender, BigInteger tokens, byte[] data, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_APPROVEANDCALL, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(spender), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(tokens), 
                new org.fisco.bcos.web3j.abi.datatypes.DynamicBytes(data)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String approveAndCallSeq(String spender, BigInteger tokens, byte[] data) {
        final Function function = new Function(
                FUNC_APPROVEANDCALL, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(spender), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(tokens), 
                new org.fisco.bcos.web3j.abi.datatypes.DynamicBytes(data)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple3<String, BigInteger, byte[]> getApproveAndCallInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_APPROVEANDCALL, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<DynamicBytes>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple3<String, BigInteger, byte[]>(

                (String) results.get(0).getValue(), 
                (BigInteger) results.get(1).getValue(), 
                (byte[]) results.get(2).getValue()
                );
    }

    public Tuple1<Boolean> getApproveAndCallOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_APPROVEANDCALL, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple1<Boolean>(

                (Boolean) results.get(0).getValue()
                );
    }

    public RemoteCall<String> newOwner() {
        final Function function = new Function(FUNC_NEWOWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<Boolean> running() {
        final Function function = new Function(FUNC_RUNNING, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteCall<TransactionReceipt> transferAnyERC20Token(String tokenAddress, BigInteger tokens) {
        final Function function = new Function(
                FUNC_TRANSFERANYERC20TOKEN, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(tokenAddress), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(tokens)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void transferAnyERC20Token(String tokenAddress, BigInteger tokens, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_TRANSFERANYERC20TOKEN, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(tokenAddress), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(tokens)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String transferAnyERC20TokenSeq(String tokenAddress, BigInteger tokens) {
        final Function function = new Function(
                FUNC_TRANSFERANYERC20TOKEN, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(tokenAddress), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(tokens)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple2<String, BigInteger> getTransferAnyERC20TokenInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_TRANSFERANYERC20TOKEN, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple2<String, BigInteger>(

                (String) results.get(0).getValue(), 
                (BigInteger) results.get(1).getValue()
                );
    }

    public Tuple1<Boolean> getTransferAnyERC20TokenOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_TRANSFERANYERC20TOKEN, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple1<Boolean>(

                (Boolean) results.get(0).getValue()
                );
    }

    public RemoteCall<BigInteger> allowance(String tokenOwner, String spender) {
        final Function function = new Function(FUNC_ALLOWANCE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(tokenOwner), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(spender)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> transferOwnership(String _newOwner) {
        final Function function = new Function(
                FUNC_TRANSFEROWNERSHIP, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_newOwner)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void transferOwnership(String _newOwner, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_TRANSFEROWNERSHIP, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_newOwner)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String transferOwnershipSeq(String _newOwner) {
        final Function function = new Function(
                FUNC_TRANSFEROWNERSHIP, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_newOwner)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple1<String> getTransferOwnershipInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_TRANSFEROWNERSHIP, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple1<String>(

                (String) results.get(0).getValue()
                );
    }

    public RemoteCall<Boolean> isBenefeciary(String param0) {
        final Function function = new Function(FUNC_ISBENEFECIARY, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public List<OwnershipTransferredEventResponse> getOwnershipTransferredEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, transactionReceipt);
        ArrayList<OwnershipTransferredEventResponse> responses = new ArrayList<OwnershipTransferredEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._from = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse._to = (String) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void registerOwnershipTransferredEventLogFilter(String fromBlock, String toBlock, List<String> otherTopcs, EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(OWNERSHIPTRANSFERRED_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,fromBlock,toBlock,otherTopcs,callback);
    }

    public void registerOwnershipTransferredEventLogFilter(EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(OWNERSHIPTRANSFERRED_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,callback);
    }

    public List<TransferEventResponse> getTransferEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(TRANSFER_EVENT, transactionReceipt);
        ArrayList<TransferEventResponse> responses = new ArrayList<TransferEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            TransferEventResponse typedResponse = new TransferEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.tokens = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void registerTransferEventLogFilter(String fromBlock, String toBlock, List<String> otherTopcs, EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(TRANSFER_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,fromBlock,toBlock,otherTopcs,callback);
    }

    public void registerTransferEventLogFilter(EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(TRANSFER_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,callback);
    }

    public List<ApprovalEventResponse> getApprovalEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(APPROVAL_EVENT, transactionReceipt);
        ArrayList<ApprovalEventResponse> responses = new ArrayList<ApprovalEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ApprovalEventResponse typedResponse = new ApprovalEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.tokenOwner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.spender = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.tokens = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void registerApprovalEventLogFilter(String fromBlock, String toBlock, List<String> otherTopcs, EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(APPROVAL_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,fromBlock,toBlock,otherTopcs,callback);
    }

    public void registerApprovalEventLogFilter(EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(APPROVAL_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,callback);
    }

    public List<FundsEventResponse> getFundsEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(FUNDS_EVENT, transactionReceipt);
        ArrayList<FundsEventResponse> responses = new ArrayList<FundsEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            FundsEventResponse typedResponse = new FundsEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.coins = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void registerFundsEventLogFilter(String fromBlock, String toBlock, List<String> otherTopcs, EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(FUNDS_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,fromBlock,toBlock,otherTopcs,callback);
    }

    public void registerFundsEventLogFilter(EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(FUNDS_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,callback);
    }

    @Deprecated
    public static FTN load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new FTN(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static FTN load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new FTN(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static FTN load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new FTN(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static FTN load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new FTN(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<FTN> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(FTN.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<FTN> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(FTN.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<FTN> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(FTN.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<FTN> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(FTN.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class OwnershipTransferredEventResponse {
        public Log log;

        public String _from;

        public String _to;
    }

    public static class TransferEventResponse {
        public Log log;

        public String from;

        public String to;

        public BigInteger tokens;
    }

    public static class ApprovalEventResponse {
        public Log log;

        public String tokenOwner;

        public String spender;

        public BigInteger tokens;
    }

    public static class FundsEventResponse {
        public Log log;

        public String from;

        public BigInteger coins;
    }
}
