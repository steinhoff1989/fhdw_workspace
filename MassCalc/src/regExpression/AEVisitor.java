package regExpression;

import exceptions.NoMoreVariablesAvailableException;
import model.Process;

public interface AEVisitor {

	//Generic not possible due to the use of handle in overrided Object.equals-method. The thrown 
	//NoMoreVariablesAvailableException would cause a different equals method that not overrides Obejct.equals!
	//T handle(AEConstant<T> regConstant);
	//T handleBoolean(AETwoPartOperation<T> regTwoPartOperation);
	
	
	Boolean handleBoolean(AEConstant regConstant);
	Process handleProcess(AEConstant regConstant)throws NoMoreVariablesAvailableException;
	Boolean handleBoolean(AETwoPartOperation regTwoPartOperation);
	Process handleProcess(AETwoPartOperation regTwoPartOperation) throws NoMoreVariablesAvailableException;
}
