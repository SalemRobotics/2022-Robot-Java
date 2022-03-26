package frc.robot;

public final class Constants {

    public final class DrivetrainConstants {
        public static final int kLeftFrontPort = 0;
        public static final int kLeftRearPort = 1;
        public static final int kRightFrontPort = 2;
        public static final int kRightRearPort = 3;

        public final class DrivetrainAutoConstants {
            public static final double ksVolts = 0.54539;
            public static final double kvVoltSecondsPerMeter = 3.3683;
            public static final double kaVoltSecondsSquaredPerMeter = 0.26098;
            public static final double trackWidthMeters = 0.635;
            public static final double maxSpeedMetersPerSecond = 3.0;
            public static final double maxAccelerationMetersPerSecondSq = 3.0;
            public static final double encoderMetersFromPulses = 10 / (2048 * 0.3192);
            public static final double metersPerSecondFromPulses = 100 / (2048 * 0.3192);
            public static final double ramseteB = 2;
            public static final double ramseteZeta = 0.7;
            public static final double kpDriveVelocity = 3.8685;
        }

        public final class DriveTrainPIDConstants {
            public static final double turnTravelUnitsPerRotation = 3600;
            public static final double pigeonUnitsPerRotation = 8192;
            public static final double P = 2.0;
            public static final double I = 0.0;
            public static final double D = 4.0;
            public static final double F = 0.0;
            public static final double Iz = 200;
            public static final double peak = 1.0;
        }
    }

    public final class XBConstants {
        public static final int drivePort = 0;
        public static final int opPort = 1;
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

}
