package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IndexConstants;

/** Indexer class that implements a single CAN SparkMax motor, as well as a ColorSensorV3 */
public class Indexer extends SubsystemBase {

    CANSparkMax indexMotor;
    ColorSensorV3 colorSensor;
    ColorMatch colorMatch;
    Color detectedColor;
    ColorMatchResult result;

    public Indexer() {
        indexMotor = new CANSparkMax(IndexConstants.indexMotorID, MotorType.kBrushless);
        colorSensor = new ColorSensorV3(I2C.Port.kOnboard);
        colorMatch = new ColorMatch();

        colorMatch.addColorMatch(Color.kRed);
        detectedColor = colorSensor.getColor();
        result = colorMatch.matchClosestColor(detectedColor);
    }

    /** Sets motor at full speed */
    public void indexIn() {
        indexMotor.set(1.0);
    }

    /** Sets motor in reverse at full speed */
    public void indexOut() {
        indexMotor.set(-1.0);
    }

    /**
     * Checks if the color sensor has detected a valid color ball.""
     * @return true if detected color is red or blue
     */
    public boolean hasCargo() {
        if ((result.color == Color.kRed) && (result.confidence >= 0.5)) return true;
        if ((result.color == Color.kBlue) && (result.confidence >= 0.5)) return true;
        return false;       
    }

    /** Halts all motor speed */
    public void halt() {
        indexMotor.set(0.0);
    }
}
