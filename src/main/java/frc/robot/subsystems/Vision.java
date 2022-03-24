package frc.robot.subsystems;

import java.util.List;

import org.photonvision.PhotonCamera;
import org.photonvision.common.hardware.VisionLEDMode;
import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.SpeedConstants;

public class Vision extends SubsystemBase {
    PhotonCamera limelight;
    List<PhotonTrackedTarget> targets;
    public Vision() {
        limelight = new PhotonCamera("gloworm");
        targets = limelight.getLatestResult().getTargets();
    }

    public double getDistance() {
        limelight.setLED(VisionLEDMode.kOn);
        if (limelight.getLatestResult().hasTargets()) {
            double targetAngle = getMaxPitch(targets); 
            double totalHeight = SpeedConstants.targetHeight - SpeedConstants.cameraHeight;
            double totalAngle = Math.tan(SpeedConstants.cameraAngle + targetAngle);
            return totalHeight / totalAngle;
        }
        return -1.0;
    }

    public double getMaxPitch(List<PhotonTrackedTarget> target) {
        double temp = 0.0;
        for (PhotonTrackedTarget e: target) {
            if (e.getPitch() > temp) {
                temp = e.getPitch();
            }
        }
        return temp;
    }

    public double getTargetYaw() {
        //TODO: Put offset here if camera is not in the middle of the robot
        double temp = 0.0;
        for (PhotonTrackedTarget e: targets) {
            temp += e.getYaw();
        }
        return temp / targets.size();
    }
}
