package frc.robot.command_groups;

import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.Auto;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

public class ShootIntakeAuto extends SequentialCommandGroup {
    public ShootIntakeAuto(Shooter shooter, Drivetrain drivetrain, Indexer index, Intake intake) {
        addCommands(
            new ParallelRaceGroup(
                new ShootIndexGroup(shooter, index),
                new WaitCommand(2)
            ),
            new ParallelRaceGroup(
                new IntakeInGroup(intake, index, shooter),
                new Auto(drivetrain)
            )
        );
    }
}
