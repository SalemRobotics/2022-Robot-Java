package frc.robot.subsystems;

import java.util.List;

import org.photonvision.PhotonCamera;
import org.photonvision.common.hardware.VisionLEDMode;
import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.SpeedConstants;

public class Vision extends SubsystemBase {
    PhotonCamera limelight;
    public Vision() {
        limelight = new PhotonCamera("gloworm");
    }

    public double getDistance() {
        limelight.setLED(VisionLEDMode.kOn);
        if (limelight.getLatestResult().hasTargets()) {
            List<PhotonTrackedTarget> targets = limelight.getLatestResult().getTargets();
            double targetAngle = targets.get(0).getPitch(); //TODO: get largest pitch in list of targets
            double totalHeight = SpeedConstants.targetHeight - SpeedConstants.cameraHeight;
            double totalAngle = Math.tan(SpeedConstants.cameraAngle + targetAngle);
            return totalHeight / totalAngle;
        }
        return -1.0;
    }

    public double getYaw() {
        List<PhotonTrackedTarget> targets = limelight.getLatestResult().getTargets();
        double middleTarget = 0.0; //TODO: add middle target sum([x.getYaw() for x in targets]) / len(targets)
        return middleTarget; //TODO: Put offset here if camera is not in the middle of the robot
    }
}
