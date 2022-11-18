package bridge.model;

import bridge.BridgeMaker;
import bridge.BridgeRandomNumberGenerator;
import bridge.util.Errors;
import bridge.util.Rules;

import java.util.ArrayList;
import java.util.List;

public class Bridge {
    private final List<Plate> bridge;

    public Bridge(int bridgeLength) {
        validateLength(bridgeLength);
        BridgeRandomNumberGenerator bridgeRandomNumberGenerator = new BridgeRandomNumberGenerator();
        BridgeMaker bridgeMaker = new BridgeMaker(bridgeRandomNumberGenerator);
        List<String> makeBridge = bridgeMaker.makeBridge(bridgeLength);
        this.bridge = bridgeStringToPlate(makeBridge);
    }

    private List<Plate> bridgeStringToPlate(List<String> bridge) {
        List<Plate> newBridge = new ArrayList<>();
        for (String symbol : bridge) {
            newBridge.add(Plate.findBySymbol(symbol));
        }
        return newBridge;
    }

    private void validateLength(int bridgeLength) {
        if(isOutOfBoundsLength(bridgeLength)) {
            throw new IllegalArgumentException(Errors.ERROR_BRIDGE_LENGTH.getMessage());
        }
    }

    private boolean isOutOfBoundsLength(int bridgeLength) {
        return (bridgeLength < Rules.BRIDGE_MIN_LENGTH)
                || (bridgeLength > Rules.BRIDGE_MAX_LENGTH);
    }

    public boolean possibleNextStep(int bridgeIndex, String inputPlate) {
        Plate userNextStep = Plate.findBySymbol(inputPlate);
        Plate currentPlate = this.bridge.get(bridgeIndex);
        return userNextStep.equals(currentPlate);
    }
}
