package frc.robot.command_groups;

import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.Auto;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Shooter;

public class ShootAutoGroup extends SequentialCommandGroup {
    public ShootAutoGroup(Shooter shooter, Drivetrain drivetrain, Indexer index) {
        addCommands(
            new ParallelRaceGroup(
                new ShootIndexGroup(shooter, index),
                new WaitCommand(2)
            ),
            new Auto(drivetrain, 0.3)
        );
    }
}
