package frc.robot;

public final class Constants {

    public final class DrivetrainConstants {
        public static final int kLeftFrontPort = 0;
        public static final int kLeftRearPort = 1;
        public static final int kRightFrontPort = 2;
        public static final int kRightRearPort = 3;
    }

    public final class XBConstants {
        public static final int drivePort = 0;
        public static final int opPort = 1;
    }

    public final class IntakeConstants {
        public static final int leftSparkMaxID = 4;
        public static final int rightSparkMaxID = 5;
        public static final int leftSolenoidChannelA = 8;
        public static final int rightSolenoidChannelA = 9;
        public static final int leftSolenoidChannelB = 4;
        public static final int rightSolenoidChannelB = 5;
    }
    
    public final class ShooterConstants {
        public static final int flywheelAPort = 8;
        public static final int flywheelBPort = 9;
    }

    public final class SpeedConstants {
        public static final double targetHeight = 8.7; //ft
        public static final double shootHeight = 2.73; //ft
        public static final double goalOffset = 2.0; //ft
        public static final double robotOffsetX = 0.0; //TODO: distance from limelight to shooter
        public static final double shootAngle = 78.93; //degrees
        public static final double gravity = 32.2; //ft/s^2
        public static final double shooterWheelCircumference = 2 * Math.PI * 2.5;
        public static final double alignmentAcceptableError = 0.5; //degrees
        public static final double cameraHeight = 34.5; //inches
        public static final double cameraAngle = 32.0; //degrees
    }
  
    public final class ClimberConstants {
        public static final int kMotorAPort = 6;
        public static final int kMotorBPort = 7;
        public static final int kForwardChannel = 10;
        public static final int kReverseChannel = 15;
        public static final int topSwitchPort = 9;
        public static final int bottomSwitchPort = 8;
        public static final double brakeTime = 0.25;
    }
}
