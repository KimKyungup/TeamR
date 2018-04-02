package teamr.hackerton18.samsung.fragmentex;

import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by up on 2018-03-31.
 */

public class JavaCodeOnly {
    private static final JavaCodeOnly ourInstance = new JavaCodeOnly();

    public static JavaCodeOnly getInstance() {
        return ourInstance;
    }

    private JavaCodeOnly() {
    }

    public String getFunctionEncoder(){
        List<Type> inputParameters = new ArrayList<>();
        Type usersId = new Utf8String("My name is cup kim");
        inputParameters.add(usersId);
        Function function = new Function("setNewOrder",
                inputParameters,
                Collections.<TypeReference<?>>emptyList());
        String functionEncoder = FunctionEncoder.encode(function);

        return functionEncoder;
    }
}
