package frc.robot.command_groups;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import frc.robot.Constants.ShooterConstants;
import frc.robot.commands.CheckSpeed;
import frc.robot.commands.IndexIn;
import frc.robot.commands.Shoot;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Shooter;


public class ShootIndexGroup extends ParallelCommandGroup {
    public ShootIndexGroup(Shooter shooter, Indexer indexer) {
        addCommands(
            new Shoot(shooter, ShooterConstants.flywheelSpeed),

            new SequentialCommandGroup(
                new WaitUntilCommand(new CheckSpeed(shooter)),

                new IndexIn(indexer, 0.69420)
            )
        );
    }
}
