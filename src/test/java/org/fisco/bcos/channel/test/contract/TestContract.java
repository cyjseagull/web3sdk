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
import org.fisco.bcos.web3j.abi.datatypes.DynamicBytes;
import org.fisco.bcos.web3j.abi.datatypes.Event;
import org.fisco.bcos.web3j.abi.datatypes.Function;
import org.fisco.bcos.web3j.abi.datatypes.Type;
import org.fisco.bcos.web3j.abi.datatypes.Utf8String;
import org.fisco.bcos.web3j.abi.datatypes.generated.Bytes32;
import org.fisco.bcos.web3j.abi.datatypes.generated.Uint256;
import org.fisco.bcos.web3j.abi.datatypes.generated.Uint8;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.crypto.EncryptType;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.RemoteCall;
import org.fisco.bcos.web3j.protocol.core.methods.response.Log;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tuples.generated.Tuple1;
import org.fisco.bcos.web3j.tuples.generated.Tuple11;
import org.fisco.bcos.web3j.tuples.generated.Tuple2;
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
public class TestContract extends Contract {
    public static String BINARY = "6080604052336000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550611307806100536000396000f300608060405260043610610083576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680631eee9b0d1461008857806361ced04f14610109578063629721fd146101aa57806375b83187146101ef57806379636cb2146102b6578063c882bf4d146103ed578063d259183d14610432575b600080fd5b34801561009457600080fd5b506100ef600480360381019080803590602001908201803590602001908080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505091929192905050506104f9565b604051808215151515815260200191505060405180910390f35b34801561011557600080fd5b50610190600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803590602001908201803590602001908080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505091929192905050506106df565b604051808215151515815260200191505060405180910390f35b3480156101b657600080fd5b506101d560048036038101908080359060200190929190505050610a64565b604051808215151515815260200191505060405180910390f35b3480156101fb57600080fd5b5061029c600480360381019080803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290505050610ad2565b604051808215151515815260200191505060405180910390f35b3480156102c257600080fd5b506103d3600480360381019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190929190803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001909291908035906020019092919080359060200190929190803590602001909291908035906020019092919080359060200190929190803590602001908201803590602001908080601f016020809104026020016040519081016040528093929190818152602001838380828437820191505050505050919291929080359060200190929190505050610af1565b604051808215151515815260200191505060405180910390f35b3480156103f957600080fd5b5061041860048036038101908080359060200190929190505050610cea565b604051808215151515815260200191505060405180910390f35b34801561043e57600080fd5b506104df600480360381019080803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290505050610ee5565b604051808215151515815260200191505060405180910390f35b6000606060003373ffffffffffffffffffffffffffffffffffffffff166000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16141561055d57600192506106d8565b600160003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020805480602002602001604051908101604052809291908181526020016000905b82821015610678578382906000526020600020018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156106645780601f1061063957610100808354040283529160200191610664565b820191906000526020600020905b81548152906001019060200180831161064757829003601f168201915b5050505050815260200190600101906105bc565b5050505091506000825111156106d357600090505b81518110156106d2576106b782828151811015156106a757fe5b9060200190602002015185610ad2565b156106c557600192506106d8565b808060010191505061068d565b5b600092505b5050919050565b6000806000803373ffffffffffffffffffffffffffffffffffffffff166000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff161415156107f5576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260448152602001807f7b22636f6465223a2031363030312c226d7367223a22736f7272792c20796f7581526020017f20646f6e27742068617665207065726d697373696f6e20746f206f706572617481526020017f652e227d0000000000000000000000000000000000000000000000000000000081525060600191505060405180910390fd5b600160008773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002092506000838054905011156109ca5760009150600090505b828054905081101561092d57610912838281548110151561086957fe5b906000526020600020018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156109075780601f106108dc57610100808354040283529160200191610907565b820191906000526020600020905b8154815290600101906020018083116108ea57829003601f168201915b505050505086610ad2565b15610920576001915061092d565b808060010191505061084c565b811515156109c9576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252603d8152602001807f7b22636f6465223a2031363030322c226d7367223a227065726d697373696f6e81526020017f206d6574686f644e616d6520616c7265616479206578697374732e227d00000081525060400191505060405180910390fd5b5b82859080600181540180825580915050906001820390600052602060002001600090919290919091509080519060200190610a06929190610fd3565b505082600160008873ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020908054610a56929190611053565b506001935050505092915050565b60008060006002805490501415610a7e5760009150610acc565b600090505b600280549050811015610ac75782600282815481101515610aa057fe5b90600052602060002001541415610aba5760019150610acc565b8080600101915050610a83565b600091505b50919050565b6000606080849150839050610ae78282610ee5565b9250505092915050565b6000610180604051908101604052808b81526020018d73ffffffffffffffffffffffffffffffffffffffff1681526020018a81526020018981526020018881526020018781526020018681526020018581526020018481526020018381526020016000604051908082528060200260200182016040528015610b825781602001602082028038833980820191505090505b5081526020016000604051908082528060200260200182016040528015610bb85781602001602082028038833980820191505090505b50815250600360008d81526020019081526020016000206000820151816000019080519060200190610beb9291906110c3565b5060208201518160010160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060408201518160020155606082015181600301556080820151816004015560a0820151816005015560c0820151816006015560e08201518160070155610100820151816008019080519060200190610c8c9291906110c3565b50610120820151816009015561014082015181600a019080519060200190610cb5929190611143565b5061016082015181600b019080519060200190610cd3929190611143565b50905050600190509b9a5050505050505050505050565b600080610d2b6040805190810160405280601481526020017f616464537570706f7274436f75706f6e547970650000000000000000000000008152506104f9565b1515610deb576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260448152602001807f7b22636f6465223a2031363030312c226d7367223a22736f7272792c20796f7581526020017f20646f6e27742068617665207065726d697373696f6e20746f206f706572617481526020017f652e227d0000000000000000000000000000000000000000000000000000000081525060600191505060405180910390fd5b60006002805490501115610e4357600090505b600280549050811015610e425782600282815481101515610e1b57fe5b90600052602060002001541415610e355760009150610edf565b8080600101915050610dfe565b5b60028390806001815401808255809150509060018203906000526020600020016000909192909190915055507f947e77308b87066799be84005794f388c9392787190a74c9bfa8f88b3e47f7f33384604051808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018281526020019250505060405180910390a1600191505b50919050565b600081518351141515610efb5760009050610fcd565b816040518082805190602001908083835b602083101515610f315780518252602082019150602081019050602083039250610f0c565b6001836020036101000a038019825116818451168082178552505050505050905001915050604051809103902060001916836040518082805190602001908083835b602083101515610f985780518252602082019150602081019050602083039250610f73565b6001836020036101000a0380198251168184511680821785525050505050509050019150506040518091039020600019161490505b92915050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061101457805160ff1916838001178555611042565b82800160010185558215611042579182015b82811115611041578251825591602001919060010190611026565b5b50905061104f9190611196565b5090565b8280548282559060005260206000209081019282156110b25760005260206000209182015b828111156110b157828290805460018160011615610100020316600290046110a19291906111bb565b5091600101919060010190611078565b5b5090506110bf9190611242565b5090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061110457805160ff1916838001178555611132565b82800160010185558215611132579182015b82811115611131578251825591602001919060010190611116565b5b50905061113f9190611196565b5090565b828054828255906000526020600020908101928215611185579160200282015b82811115611184578251829060001916905591602001919060010190611163565b5b509050611192919061126e565b5090565b6111b891905b808211156111b457600081600090555060010161119c565b5090565b90565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106111f45780548555611231565b8280016001018555821561123157600052602060002091601f016020900482015b82811115611230578254825591600101919060010190611215565b5b50905061123e9190611196565b5090565b61126b91905b80821115611267576000818161125e9190611293565b50600101611248565b5090565b90565b61129091905b8082111561128c576000816000905550600101611274565b5090565b90565b50805460018160011615610100020316600290046000825580601f106112b957506112d8565b601f0160209004906000526020600020908101906112d79190611196565b5b505600a165627a7a72305820ac3ad70b8e5c4c254f2adae80d5d59b0ec3c0601f293f6e692c669ebdab238b40029";

    public static final String ABI = "[{\"constant\":true,\"inputs\":[{\"name\":\"_methodName\",\"type\":\"string\"}],\"name\":\"validPermission\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_accountAddress\",\"type\":\"address\"},{\"name\":\"_methodName\",\"type\":\"string\"}],\"name\":\"addPermission\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_couponType\",\"type\":\"uint256\"}],\"name\":\"verifyCouponType\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"a\",\"type\":\"string\"},{\"name\":\"b\",\"type\":\"string\"}],\"name\":\"compareString\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_ownerMerchantAddress\",\"type\":\"address\"},{\"name\":\"_batchId\",\"type\":\"uint256\"},{\"name\":\"_secretKey\",\"type\":\"bytes\"},{\"name\":\"_couponType\",\"type\":\"uint256\"},{\"name\":\"_status\",\"type\":\"uint256\"},{\"name\":\"_startTime\",\"type\":\"uint256\"},{\"name\":\"_endTime\",\"type\":\"uint256\"},{\"name\":\"_allowUseStartTime\",\"type\":\"uint256\"},{\"name\":\"_allowUseEndTime\",\"type\":\"uint256\"},{\"name\":\"_rule\",\"type\":\"bytes\"},{\"name\":\"_count\",\"type\":\"uint256\"}],\"name\":\"create\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_couponType\",\"type\":\"uint256\"}],\"name\":\"addSupportCouponType\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"a\",\"type\":\"bytes\"},{\"name\":\"b\",\"type\":\"bytes\"}],\"name\":\"compareBytes\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"logInfo\",\"type\":\"bytes\"}],\"name\":\"SIMPLE_LOG\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"operateAddress\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"_couponType\",\"type\":\"uint256\"}],\"name\":\"OPERATE_ADD_SUPPORT_COUPON_TYPE\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"action\",\"type\":\"uint8\"},{\"indexed\":false,\"name\":\"operateAddress\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"_ownerMerchantAddress\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"_batchId\",\"type\":\"uint256\"},{\"indexed\":false,\"name\":\"_couponType\",\"type\":\"uint256\"},{\"indexed\":false,\"name\":\"_status\",\"type\":\"uint256\"},{\"indexed\":false,\"name\":\"_startTime\",\"type\":\"uint256\"},{\"indexed\":false,\"name\":\"_endTime\",\"type\":\"uint256\"},{\"indexed\":false,\"name\":\"_allowUseStartTime\",\"type\":\"uint256\"},{\"indexed\":false,\"name\":\"_allowUseEndTime\",\"type\":\"uint256\"},{\"indexed\":false,\"name\":\"_rule\",\"type\":\"bytes\"},{\"indexed\":false,\"name\":\"_count\",\"type\":\"uint256\"}],\"name\":\"OPERATE_COUPON_BATCH_CREATE\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"action\",\"type\":\"uint8\"},{\"indexed\":false,\"name\":\"operateAddress\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"batchId\",\"type\":\"uint256\"}],\"name\":\"OPERATE_COUPON_BATCH_SET_STATUS\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"action\",\"type\":\"uint8\"},{\"indexed\":false,\"name\":\"operateAddress\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"batchId\",\"type\":\"uint256\"},{\"indexed\":false,\"name\":\"count\",\"type\":\"uint256\"}],\"name\":\"OPERATE_COUPON_BATCH_SET_COUNT\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"action\",\"type\":\"uint8\"},{\"indexed\":false,\"name\":\"operateAddress\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"batchId\",\"type\":\"uint256\"},{\"indexed\":false,\"name\":\"consumerAddress\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"couponId\",\"type\":\"bytes32\"},{\"indexed\":false,\"name\":\"couponAmount\",\"type\":\"uint256\"},{\"indexed\":false,\"name\":\"obtainTime\",\"type\":\"uint256\"}],\"name\":\"OPERATE_RECEIVE\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"action\",\"type\":\"uint8\"},{\"indexed\":false,\"name\":\"operateAddress\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"fromAddress\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"toAddress\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"couponId\",\"type\":\"bytes32\"},{\"indexed\":false,\"name\":\"obtainTime\",\"type\":\"uint256\"}],\"name\":\"OPERATE_TRANSFER\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"action\",\"type\":\"uint8\"},{\"indexed\":false,\"name\":\"operateAddress\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"couponId\",\"type\":\"bytes32\"},{\"indexed\":false,\"name\":\"cancelUserAddress\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"consumeTime\",\"type\":\"uint256\"},{\"indexed\":false,\"name\":\"consumeAmount\",\"type\":\"uint256\"}],\"name\":\"OPERATE_CANCEL\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"action\",\"type\":\"uint8\"},{\"indexed\":false,\"name\":\"operateAddress\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"couponId\",\"type\":\"bytes32\"},{\"indexed\":false,\"name\":\"cancelUserAddress\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"consumeTime\",\"type\":\"uint256\"},{\"indexed\":false,\"name\":\"consumeAmount\",\"type\":\"uint256\"},{\"indexed\":false,\"name\":\"couponAmount\",\"type\":\"uint256\"}],\"name\":\"OPERATE_CANCEL_M\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"action\",\"type\":\"uint8\"},{\"indexed\":false,\"name\":\"operateAddress\",\"type\":\"address\"},{\"indexed\":false,\"name\":\"couponId\",\"type\":\"bytes32\"}],\"name\":\"OPERATE_EXPIRE\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"isSuccess\",\"type\":\"bool\"},{\"indexed\":false,\"name\":\"desc\",\"type\":\"string\"}],\"name\":\"LogEvent\",\"type\":\"event\"}]";

    public static final TransactionDecoder transactionDecoder = new TransactionDecoder(ABI, BINARY);

    public static String SM_BINARY = "6080604052336000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550611307806100536000396000f300608060405260043610610083576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680634626c650146100885780635720b12b1461014f5780635f6d911814610194578063689e4a8a1461023557806380ee9657146102b65780638a9af3ca146102fb578063a111107814610432575b600080fd5b34801561009457600080fd5b50610135600480360381019080803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505091929192905050506104f9565b604051808215151515815260200191505060405180910390f35b34801561015b57600080fd5b5061017a60048036038101908080359060200190929190505050610518565b604051808215151515815260200191505060405180910390f35b3480156101a057600080fd5b5061021b600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290505050610713565b604051808215151515815260200191505060405180910390f35b34801561024157600080fd5b5061029c600480360381019080803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290505050610a98565b604051808215151515815260200191505060405180910390f35b3480156102c257600080fd5b506102e160048036038101908080359060200190929190505050610c7e565b604051808215151515815260200191505060405180910390f35b34801561030757600080fd5b50610418600480360381019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080359060200190929190803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001909291908035906020019092919080359060200190929190803590602001909291908035906020019092919080359060200190929190803590602001908201803590602001908080601f016020809104026020016040519081016040528093929190818152602001838380828437820191505050505050919291929080359060200190929190505050610cec565b604051808215151515815260200191505060405180910390f35b34801561043e57600080fd5b506104df600480360381019080803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290505050610ee5565b604051808215151515815260200191505060405180910390f35b600060608084915083905061050e8282610ee5565b9250505092915050565b6000806105596040805190810160405280601481526020017f616464537570706f7274436f75706f6e54797065000000000000000000000000815250610a98565b1515610619576040517fc703cb120000000000000000000000000000000000000000000000000000000081526004018080602001828103825260448152602001807f7b22636f6465223a2031363030312c226d7367223a22736f7272792c20796f7581526020017f20646f6e27742068617665207065726d697373696f6e20746f206f706572617481526020017f652e227d0000000000000000000000000000000000000000000000000000000081525060600191505060405180910390fd5b6000600280549050111561067157600090505b600280549050811015610670578260028281548110151561064957fe5b90600052602060002001541415610663576000915061070d565b808060010191505061062c565b5b60028390806001815401808255809150509060018203906000526020600020016000909192909190915055507f80ef73771c9c90fb38be59bbfe0f504f428b32105181409999d1f031c73261333384604051808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018281526020019250505060405180910390a1600191505b50919050565b6000806000803373ffffffffffffffffffffffffffffffffffffffff166000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16141515610829576040517fc703cb120000000000000000000000000000000000000000000000000000000081526004018080602001828103825260448152602001807f7b22636f6465223a2031363030312c226d7367223a22736f7272792c20796f7581526020017f20646f6e27742068617665207065726d697373696f6e20746f206f706572617481526020017f652e227d0000000000000000000000000000000000000000000000000000000081525060600191505060405180910390fd5b600160008773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002092506000838054905011156109fe5760009150600090505b828054905081101561096157610946838281548110151561089d57fe5b906000526020600020018054600181600116156101000203166002900480601f01602080910402602001604051908101604052809291908181526020018280546001816001161561010002031660029004801561093b5780601f106109105761010080835404028352916020019161093b565b820191906000526020600020905b81548152906001019060200180831161091e57829003601f168201915b5050505050866104f9565b156109545760019150610961565b8080600101915050610880565b811515156109fd576040517fc703cb1200000000000000000000000000000000000000000000000000000000815260040180806020018281038252603d8152602001807f7b22636f6465223a2031363030322c226d7367223a227065726d697373696f6e81526020017f206d6574686f644e616d6520616c7265616479206578697374732e227d00000081525060400191505060405180910390fd5b5b82859080600181540180825580915050906001820390600052602060002001600090919290919091509080519060200190610a3a929190610fd3565b505082600160008873ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020908054610a8a929190611053565b506001935050505092915050565b6000606060003373ffffffffffffffffffffffffffffffffffffffff166000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff161415610afc5760019250610c77565b600160003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020805480602002602001604051908101604052809291908181526020016000905b82821015610c17578382906000526020600020018054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610c035780601f10610bd857610100808354040283529160200191610c03565b820191906000526020600020905b815481529060010190602001808311610be657829003601f168201915b505050505081526020019060010190610b5b565b505050509150600082511115610c7257600090505b8151811015610c7157610c568282815181101515610c4657fe5b90602001906020020151856104f9565b15610c645760019250610c77565b8080600101915050610c2c565b5b600092505b5050919050565b60008060006002805490501415610c985760009150610ce6565b600090505b600280549050811015610ce15782600282815481101515610cba57fe5b90600052602060002001541415610cd45760019150610ce6565b8080600101915050610c9d565b600091505b50919050565b6000610180604051908101604052808b81526020018d73ffffffffffffffffffffffffffffffffffffffff1681526020018a81526020018981526020018881526020018781526020018681526020018581526020018481526020018381526020016000604051908082528060200260200182016040528015610d7d5781602001602082028038833980820191505090505b5081526020016000604051908082528060200260200182016040528015610db35781602001602082028038833980820191505090505b50815250600360008d81526020019081526020016000206000820151816000019080519060200190610de69291906110c3565b5060208201518160010160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060408201518160020155606082015181600301556080820151816004015560a0820151816005015560c0820151816006015560e08201518160070155610100820151816008019080519060200190610e879291906110c3565b50610120820151816009015561014082015181600a019080519060200190610eb0929190611143565b5061016082015181600b019080519060200190610ece929190611143565b50905050600190509b9a5050505050505050505050565b600081518351141515610efb5760009050610fcd565b816040518082805190602001908083835b602083101515610f315780518252602082019150602081019050602083039250610f0c565b6001836020036101000a038019825116818451168082178552505050505050905001915050604051809103902060001916836040518082805190602001908083835b602083101515610f985780518252602082019150602081019050602083039250610f73565b6001836020036101000a0380198251168184511680821785525050505050509050019150506040518091039020600019161490505b92915050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061101457805160ff1916838001178555611042565b82800160010185558215611042579182015b82811115611041578251825591602001919060010190611026565b5b50905061104f9190611196565b5090565b8280548282559060005260206000209081019282156110b25760005260206000209182015b828111156110b157828290805460018160011615610100020316600290046110a19291906111bb565b5091600101919060010190611078565b5b5090506110bf9190611242565b5090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061110457805160ff1916838001178555611132565b82800160010185558215611132579182015b82811115611131578251825591602001919060010190611116565b5b50905061113f9190611196565b5090565b828054828255906000526020600020908101928215611185579160200282015b82811115611184578251829060001916905591602001919060010190611163565b5b509050611192919061126e565b5090565b6111b891905b808211156111b457600081600090555060010161119c565b5090565b90565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106111f45780548555611231565b8280016001018555821561123157600052602060002091601f016020900482015b82811115611230578254825591600101919060010190611215565b5b50905061123e9190611196565b5090565b61126b91905b80821115611267576000818161125e9190611293565b50600101611248565b5090565b90565b61129091905b8082111561128c576000816000905550600101611274565b5090565b90565b50805460018160011615610100020316600290046000825580601f106112b957506112d8565b601f0160209004906000526020600020908101906112d79190611196565b5b505600a165627a7a72305820441dc7da134c563aa345dae8fdf356ad033105bd09fbf5a1ac6502e1b58726780029";

    public static final String FUNC_VALIDPERMISSION = "validPermission";

    public static final String FUNC_ADDPERMISSION = "addPermission";

    public static final String FUNC_VERIFYCOUPONTYPE = "verifyCouponType";

    public static final String FUNC_COMPARESTRING = "compareString";

    public static final String FUNC_CREATE = "create";

    public static final String FUNC_ADDSUPPORTCOUPONTYPE = "addSupportCouponType";

    public static final String FUNC_COMPAREBYTES = "compareBytes";

    public static final Event SIMPLE_LOG_EVENT = new Event("SIMPLE_LOG", 
            Arrays.<TypeReference<?>>asList(new TypeReference<DynamicBytes>() {}));
    ;

    public static final Event OPERATE_ADD_SUPPORT_COUPON_TYPE_EVENT = new Event("OPERATE_ADD_SUPPORT_COUPON_TYPE", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event OPERATE_COUPON_BATCH_CREATE_EVENT = new Event("OPERATE_COUPON_BATCH_CREATE", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}, new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<DynamicBytes>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event OPERATE_COUPON_BATCH_SET_STATUS_EVENT = new Event("OPERATE_COUPON_BATCH_SET_STATUS", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event OPERATE_COUPON_BATCH_SET_COUNT_EVENT = new Event("OPERATE_COUPON_BATCH_SET_COUNT", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event OPERATE_RECEIVE_EVENT = new Event("OPERATE_RECEIVE", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Address>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event OPERATE_TRANSFER_EVENT = new Event("OPERATE_TRANSFER", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}, new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event OPERATE_CANCEL_EVENT = new Event("OPERATE_CANCEL", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}, new TypeReference<Address>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event OPERATE_CANCEL_M_EVENT = new Event("OPERATE_CANCEL_M", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}, new TypeReference<Address>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event OPERATE_EXPIRE_EVENT = new Event("OPERATE_EXPIRE", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}, new TypeReference<Address>() {}, new TypeReference<Bytes32>() {}));
    ;

    public static final Event LOGEVENT_EVENT = new Event("LogEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}, new TypeReference<Utf8String>() {}));
    ;

    @Deprecated
    protected TestContract(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(getBinary(), contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected TestContract(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(getBinary(), contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected TestContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(getBinary(), contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected TestContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(getBinary(), contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static String getBinary() {
        return (EncryptType.encryptType == EncryptType.ECDSA_TYPE ? BINARY : SM_BINARY);
    }

    public static TransactionDecoder getTransactionDecoder() {
        return transactionDecoder;
    }

    public RemoteCall<Boolean> validPermission(String _methodName) {
        final Function function = new Function(FUNC_VALIDPERMISSION, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(_methodName)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteCall<TransactionReceipt> addPermission(String _accountAddress, String _methodName) {
        final Function function = new Function(
                FUNC_ADDPERMISSION, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_accountAddress), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(_methodName)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void addPermission(String _accountAddress, String _methodName, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_ADDPERMISSION, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_accountAddress), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(_methodName)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String addPermissionSeq(String _accountAddress, String _methodName) {
        final Function function = new Function(
                FUNC_ADDPERMISSION, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_accountAddress), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(_methodName)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple2<String, String> getAddPermissionInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_ADDPERMISSION, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple2<String, String>(

                (String) results.get(0).getValue(), 
                (String) results.get(1).getValue()
                );
    }

    public Tuple1<Boolean> getAddPermissionOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_ADDPERMISSION, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple1<Boolean>(

                (Boolean) results.get(0).getValue()
                );
    }

    public RemoteCall<TransactionReceipt> verifyCouponType(BigInteger _couponType) {
        final Function function = new Function(
                FUNC_VERIFYCOUPONTYPE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_couponType)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void verifyCouponType(BigInteger _couponType, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_VERIFYCOUPONTYPE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_couponType)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String verifyCouponTypeSeq(BigInteger _couponType) {
        final Function function = new Function(
                FUNC_VERIFYCOUPONTYPE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_couponType)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple1<BigInteger> getVerifyCouponTypeInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_VERIFYCOUPONTYPE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple1<BigInteger>(

                (BigInteger) results.get(0).getValue()
                );
    }

    public Tuple1<Boolean> getVerifyCouponTypeOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_VERIFYCOUPONTYPE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple1<Boolean>(

                (Boolean) results.get(0).getValue()
                );
    }

    public RemoteCall<Boolean> compareString(String a, String b) {
        final Function function = new Function(FUNC_COMPARESTRING, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(a), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(b)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteCall<TransactionReceipt> create(String _ownerMerchantAddress, BigInteger _batchId, byte[] _secretKey, BigInteger _couponType, BigInteger _status, BigInteger _startTime, BigInteger _endTime, BigInteger _allowUseStartTime, BigInteger _allowUseEndTime, byte[] _rule, BigInteger _count) {
        final Function function = new Function(
                FUNC_CREATE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_ownerMerchantAddress), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_batchId), 
                new org.fisco.bcos.web3j.abi.datatypes.DynamicBytes(_secretKey), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_couponType), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_status), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_startTime), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_endTime), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_allowUseStartTime), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_allowUseEndTime), 
                new org.fisco.bcos.web3j.abi.datatypes.DynamicBytes(_rule), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_count)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void create(String _ownerMerchantAddress, BigInteger _batchId, byte[] _secretKey, BigInteger _couponType, BigInteger _status, BigInteger _startTime, BigInteger _endTime, BigInteger _allowUseStartTime, BigInteger _allowUseEndTime, byte[] _rule, BigInteger _count, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_CREATE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_ownerMerchantAddress), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_batchId), 
                new org.fisco.bcos.web3j.abi.datatypes.DynamicBytes(_secretKey), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_couponType), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_status), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_startTime), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_endTime), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_allowUseStartTime), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_allowUseEndTime), 
                new org.fisco.bcos.web3j.abi.datatypes.DynamicBytes(_rule), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_count)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String createSeq(String _ownerMerchantAddress, BigInteger _batchId, byte[] _secretKey, BigInteger _couponType, BigInteger _status, BigInteger _startTime, BigInteger _endTime, BigInteger _allowUseStartTime, BigInteger _allowUseEndTime, byte[] _rule, BigInteger _count) {
        final Function function = new Function(
                FUNC_CREATE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_ownerMerchantAddress), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_batchId), 
                new org.fisco.bcos.web3j.abi.datatypes.DynamicBytes(_secretKey), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_couponType), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_status), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_startTime), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_endTime), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_allowUseStartTime), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_allowUseEndTime), 
                new org.fisco.bcos.web3j.abi.datatypes.DynamicBytes(_rule), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_count)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple11<String, BigInteger, byte[], BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, byte[], BigInteger> getCreateInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_CREATE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<DynamicBytes>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<DynamicBytes>() {}, new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple11<String, BigInteger, byte[], BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, byte[], BigInteger>(

                (String) results.get(0).getValue(), 
                (BigInteger) results.get(1).getValue(), 
                (byte[]) results.get(2).getValue(), 
                (BigInteger) results.get(3).getValue(), 
                (BigInteger) results.get(4).getValue(), 
                (BigInteger) results.get(5).getValue(), 
                (BigInteger) results.get(6).getValue(), 
                (BigInteger) results.get(7).getValue(), 
                (BigInteger) results.get(8).getValue(), 
                (byte[]) results.get(9).getValue(), 
                (BigInteger) results.get(10).getValue()
                );
    }

    public Tuple1<Boolean> getCreateOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_CREATE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple1<Boolean>(

                (Boolean) results.get(0).getValue()
                );
    }

    public RemoteCall<TransactionReceipt> addSupportCouponType(BigInteger _couponType) {
        final Function function = new Function(
                FUNC_ADDSUPPORTCOUPONTYPE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_couponType)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void addSupportCouponType(BigInteger _couponType, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_ADDSUPPORTCOUPONTYPE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_couponType)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String addSupportCouponTypeSeq(BigInteger _couponType) {
        final Function function = new Function(
                FUNC_ADDSUPPORTCOUPONTYPE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_couponType)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple1<BigInteger> getAddSupportCouponTypeInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_ADDSUPPORTCOUPONTYPE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple1<BigInteger>(

                (BigInteger) results.get(0).getValue()
                );
    }

    public Tuple1<Boolean> getAddSupportCouponTypeOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_ADDSUPPORTCOUPONTYPE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple1<Boolean>(

                (Boolean) results.get(0).getValue()
                );
    }

    public RemoteCall<Boolean> compareBytes(byte[] a, byte[] b) {
        final Function function = new Function(FUNC_COMPAREBYTES, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.DynamicBytes(a), 
                new org.fisco.bcos.web3j.abi.datatypes.DynamicBytes(b)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public List<SIMPLE_LOGEventResponse> getSIMPLE_LOGEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(SIMPLE_LOG_EVENT, transactionReceipt);
        ArrayList<SIMPLE_LOGEventResponse> responses = new ArrayList<SIMPLE_LOGEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            SIMPLE_LOGEventResponse typedResponse = new SIMPLE_LOGEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.logInfo = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void registerSIMPLE_LOGEventLogFilter(String fromBlock, String toBlock, List<String> otherTopcs, EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(SIMPLE_LOG_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,fromBlock,toBlock,otherTopcs,callback);
    }

    public void registerSIMPLE_LOGEventLogFilter(EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(SIMPLE_LOG_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,callback);
    }

    public List<OPERATE_ADD_SUPPORT_COUPON_TYPEEventResponse> getOPERATE_ADD_SUPPORT_COUPON_TYPEEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(OPERATE_ADD_SUPPORT_COUPON_TYPE_EVENT, transactionReceipt);
        ArrayList<OPERATE_ADD_SUPPORT_COUPON_TYPEEventResponse> responses = new ArrayList<OPERATE_ADD_SUPPORT_COUPON_TYPEEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            OPERATE_ADD_SUPPORT_COUPON_TYPEEventResponse typedResponse = new OPERATE_ADD_SUPPORT_COUPON_TYPEEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.operateAddress = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse._couponType = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void registerOPERATE_ADD_SUPPORT_COUPON_TYPEEventLogFilter(String fromBlock, String toBlock, List<String> otherTopcs, EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(OPERATE_ADD_SUPPORT_COUPON_TYPE_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,fromBlock,toBlock,otherTopcs,callback);
    }

    public void registerOPERATE_ADD_SUPPORT_COUPON_TYPEEventLogFilter(EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(OPERATE_ADD_SUPPORT_COUPON_TYPE_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,callback);
    }

    public List<OPERATE_COUPON_BATCH_CREATEEventResponse> getOPERATE_COUPON_BATCH_CREATEEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(OPERATE_COUPON_BATCH_CREATE_EVENT, transactionReceipt);
        ArrayList<OPERATE_COUPON_BATCH_CREATEEventResponse> responses = new ArrayList<OPERATE_COUPON_BATCH_CREATEEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            OPERATE_COUPON_BATCH_CREATEEventResponse typedResponse = new OPERATE_COUPON_BATCH_CREATEEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.action = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.operateAddress = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse._ownerMerchantAddress = (String) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse._batchId = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse._couponType = (BigInteger) eventValues.getNonIndexedValues().get(4).getValue();
            typedResponse._status = (BigInteger) eventValues.getNonIndexedValues().get(5).getValue();
            typedResponse._startTime = (BigInteger) eventValues.getNonIndexedValues().get(6).getValue();
            typedResponse._endTime = (BigInteger) eventValues.getNonIndexedValues().get(7).getValue();
            typedResponse._allowUseStartTime = (BigInteger) eventValues.getNonIndexedValues().get(8).getValue();
            typedResponse._allowUseEndTime = (BigInteger) eventValues.getNonIndexedValues().get(9).getValue();
            typedResponse._rule = (byte[]) eventValues.getNonIndexedValues().get(10).getValue();
            typedResponse._count = (BigInteger) eventValues.getNonIndexedValues().get(11).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void registerOPERATE_COUPON_BATCH_CREATEEventLogFilter(String fromBlock, String toBlock, List<String> otherTopcs, EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(OPERATE_COUPON_BATCH_CREATE_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,fromBlock,toBlock,otherTopcs,callback);
    }

    public void registerOPERATE_COUPON_BATCH_CREATEEventLogFilter(EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(OPERATE_COUPON_BATCH_CREATE_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,callback);
    }

    public List<OPERATE_COUPON_BATCH_SET_STATUSEventResponse> getOPERATE_COUPON_BATCH_SET_STATUSEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(OPERATE_COUPON_BATCH_SET_STATUS_EVENT, transactionReceipt);
        ArrayList<OPERATE_COUPON_BATCH_SET_STATUSEventResponse> responses = new ArrayList<OPERATE_COUPON_BATCH_SET_STATUSEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            OPERATE_COUPON_BATCH_SET_STATUSEventResponse typedResponse = new OPERATE_COUPON_BATCH_SET_STATUSEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.action = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.operateAddress = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.batchId = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void registerOPERATE_COUPON_BATCH_SET_STATUSEventLogFilter(String fromBlock, String toBlock, List<String> otherTopcs, EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(OPERATE_COUPON_BATCH_SET_STATUS_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,fromBlock,toBlock,otherTopcs,callback);
    }

    public void registerOPERATE_COUPON_BATCH_SET_STATUSEventLogFilter(EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(OPERATE_COUPON_BATCH_SET_STATUS_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,callback);
    }

    public List<OPERATE_COUPON_BATCH_SET_COUNTEventResponse> getOPERATE_COUPON_BATCH_SET_COUNTEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(OPERATE_COUPON_BATCH_SET_COUNT_EVENT, transactionReceipt);
        ArrayList<OPERATE_COUPON_BATCH_SET_COUNTEventResponse> responses = new ArrayList<OPERATE_COUPON_BATCH_SET_COUNTEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            OPERATE_COUPON_BATCH_SET_COUNTEventResponse typedResponse = new OPERATE_COUPON_BATCH_SET_COUNTEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.action = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.operateAddress = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.batchId = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.count = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void registerOPERATE_COUPON_BATCH_SET_COUNTEventLogFilter(String fromBlock, String toBlock, List<String> otherTopcs, EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(OPERATE_COUPON_BATCH_SET_COUNT_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,fromBlock,toBlock,otherTopcs,callback);
    }

    public void registerOPERATE_COUPON_BATCH_SET_COUNTEventLogFilter(EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(OPERATE_COUPON_BATCH_SET_COUNT_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,callback);
    }

    public List<OPERATE_RECEIVEEventResponse> getOPERATE_RECEIVEEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(OPERATE_RECEIVE_EVENT, transactionReceipt);
        ArrayList<OPERATE_RECEIVEEventResponse> responses = new ArrayList<OPERATE_RECEIVEEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            OPERATE_RECEIVEEventResponse typedResponse = new OPERATE_RECEIVEEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.action = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.operateAddress = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.batchId = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.consumerAddress = (String) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse.couponId = (byte[]) eventValues.getNonIndexedValues().get(4).getValue();
            typedResponse.couponAmount = (BigInteger) eventValues.getNonIndexedValues().get(5).getValue();
            typedResponse.obtainTime = (BigInteger) eventValues.getNonIndexedValues().get(6).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void registerOPERATE_RECEIVEEventLogFilter(String fromBlock, String toBlock, List<String> otherTopcs, EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(OPERATE_RECEIVE_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,fromBlock,toBlock,otherTopcs,callback);
    }

    public void registerOPERATE_RECEIVEEventLogFilter(EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(OPERATE_RECEIVE_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,callback);
    }

    public List<OPERATE_TRANSFEREventResponse> getOPERATE_TRANSFEREvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(OPERATE_TRANSFER_EVENT, transactionReceipt);
        ArrayList<OPERATE_TRANSFEREventResponse> responses = new ArrayList<OPERATE_TRANSFEREventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            OPERATE_TRANSFEREventResponse typedResponse = new OPERATE_TRANSFEREventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.action = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.operateAddress = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.fromAddress = (String) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.toAddress = (String) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse.couponId = (byte[]) eventValues.getNonIndexedValues().get(4).getValue();
            typedResponse.obtainTime = (BigInteger) eventValues.getNonIndexedValues().get(5).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void registerOPERATE_TRANSFEREventLogFilter(String fromBlock, String toBlock, List<String> otherTopcs, EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(OPERATE_TRANSFER_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,fromBlock,toBlock,otherTopcs,callback);
    }

    public void registerOPERATE_TRANSFEREventLogFilter(EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(OPERATE_TRANSFER_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,callback);
    }

    public List<OPERATE_CANCELEventResponse> getOPERATE_CANCELEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(OPERATE_CANCEL_EVENT, transactionReceipt);
        ArrayList<OPERATE_CANCELEventResponse> responses = new ArrayList<OPERATE_CANCELEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            OPERATE_CANCELEventResponse typedResponse = new OPERATE_CANCELEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.action = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.operateAddress = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.couponId = (byte[]) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.cancelUserAddress = (String) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse.consumeTime = (BigInteger) eventValues.getNonIndexedValues().get(4).getValue();
            typedResponse.consumeAmount = (BigInteger) eventValues.getNonIndexedValues().get(5).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void registerOPERATE_CANCELEventLogFilter(String fromBlock, String toBlock, List<String> otherTopcs, EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(OPERATE_CANCEL_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,fromBlock,toBlock,otherTopcs,callback);
    }

    public void registerOPERATE_CANCELEventLogFilter(EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(OPERATE_CANCEL_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,callback);
    }

    public List<OPERATE_CANCEL_MEventResponse> getOPERATE_CANCEL_MEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(OPERATE_CANCEL_M_EVENT, transactionReceipt);
        ArrayList<OPERATE_CANCEL_MEventResponse> responses = new ArrayList<OPERATE_CANCEL_MEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            OPERATE_CANCEL_MEventResponse typedResponse = new OPERATE_CANCEL_MEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.action = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.operateAddress = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.couponId = (byte[]) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.cancelUserAddress = (String) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse.consumeTime = (BigInteger) eventValues.getNonIndexedValues().get(4).getValue();
            typedResponse.consumeAmount = (BigInteger) eventValues.getNonIndexedValues().get(5).getValue();
            typedResponse.couponAmount = (BigInteger) eventValues.getNonIndexedValues().get(6).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void registerOPERATE_CANCEL_MEventLogFilter(String fromBlock, String toBlock, List<String> otherTopcs, EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(OPERATE_CANCEL_M_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,fromBlock,toBlock,otherTopcs,callback);
    }

    public void registerOPERATE_CANCEL_MEventLogFilter(EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(OPERATE_CANCEL_M_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,callback);
    }

    public List<OPERATE_EXPIREEventResponse> getOPERATE_EXPIREEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(OPERATE_EXPIRE_EVENT, transactionReceipt);
        ArrayList<OPERATE_EXPIREEventResponse> responses = new ArrayList<OPERATE_EXPIREEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            OPERATE_EXPIREEventResponse typedResponse = new OPERATE_EXPIREEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.action = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.operateAddress = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.couponId = (byte[]) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void registerOPERATE_EXPIREEventLogFilter(String fromBlock, String toBlock, List<String> otherTopcs, EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(OPERATE_EXPIRE_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,fromBlock,toBlock,otherTopcs,callback);
    }

    public void registerOPERATE_EXPIREEventLogFilter(EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(OPERATE_EXPIRE_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,callback);
    }

    public List<LogEventEventResponse> getLogEventEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(LOGEVENT_EVENT, transactionReceipt);
        ArrayList<LogEventEventResponse> responses = new ArrayList<LogEventEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            LogEventEventResponse typedResponse = new LogEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.isSuccess = (Boolean) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.desc = (String) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void registerLogEventEventLogFilter(String fromBlock, String toBlock, List<String> otherTopcs, EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(LOGEVENT_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,fromBlock,toBlock,otherTopcs,callback);
    }

    public void registerLogEventEventLogFilter(EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(LOGEVENT_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,callback);
    }

    @Deprecated
    public static TestContract load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new TestContract(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static TestContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new TestContract(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static TestContract load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new TestContract(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static TestContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new TestContract(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<TestContract> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(TestContract.class, web3j, credentials, contractGasProvider, getBinary(), "");
    }

    @Deprecated
    public static RemoteCall<TestContract> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(TestContract.class, web3j, credentials, gasPrice, gasLimit, getBinary(), "");
    }

    public static RemoteCall<TestContract> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(TestContract.class, web3j, transactionManager, contractGasProvider, getBinary(), "");
    }

    @Deprecated
    public static RemoteCall<TestContract> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(TestContract.class, web3j, transactionManager, gasPrice, gasLimit, getBinary(), "");
    }

    public static class SIMPLE_LOGEventResponse {
        public Log log;

        public byte[] logInfo;
    }

    public static class OPERATE_ADD_SUPPORT_COUPON_TYPEEventResponse {
        public Log log;

        public String operateAddress;

        public BigInteger _couponType;
    }

    public static class OPERATE_COUPON_BATCH_CREATEEventResponse {
        public Log log;

        public BigInteger action;

        public String operateAddress;

        public String _ownerMerchantAddress;

        public BigInteger _batchId;

        public BigInteger _couponType;

        public BigInteger _status;

        public BigInteger _startTime;

        public BigInteger _endTime;

        public BigInteger _allowUseStartTime;

        public BigInteger _allowUseEndTime;

        public byte[] _rule;

        public BigInteger _count;
    }

    public static class OPERATE_COUPON_BATCH_SET_STATUSEventResponse {
        public Log log;

        public BigInteger action;

        public String operateAddress;

        public BigInteger batchId;
    }

    public static class OPERATE_COUPON_BATCH_SET_COUNTEventResponse {
        public Log log;

        public BigInteger action;

        public String operateAddress;

        public BigInteger batchId;

        public BigInteger count;
    }

    public static class OPERATE_RECEIVEEventResponse {
        public Log log;

        public BigInteger action;

        public String operateAddress;

        public BigInteger batchId;

        public String consumerAddress;

        public byte[] couponId;

        public BigInteger couponAmount;

        public BigInteger obtainTime;
    }

    public static class OPERATE_TRANSFEREventResponse {
        public Log log;

        public BigInteger action;

        public String operateAddress;

        public String fromAddress;

        public String toAddress;

        public byte[] couponId;

        public BigInteger obtainTime;
    }

    public static class OPERATE_CANCELEventResponse {
        public Log log;

        public BigInteger action;

        public String operateAddress;

        public byte[] couponId;

        public String cancelUserAddress;

        public BigInteger consumeTime;

        public BigInteger consumeAmount;
    }

    public static class OPERATE_CANCEL_MEventResponse {
        public Log log;

        public BigInteger action;

        public String operateAddress;

        public byte[] couponId;

        public String cancelUserAddress;

        public BigInteger consumeTime;

        public BigInteger consumeAmount;

        public BigInteger couponAmount;
    }

    public static class OPERATE_EXPIREEventResponse {
        public Log log;

        public BigInteger action;

        public String operateAddress;

        public byte[] couponId;
    }

    public static class LogEventEventResponse {
        public Log log;

        public Boolean isSuccess;

        public String desc;
    }
}
