package frc.robot.command_groups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.IndexIn;
import frc.robot.commands.Shoot;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Shooter;


public class ShootIndexGroup extends SequentialCommandGroup {
    public ShootIndexGroup(Shooter shooter, Indexer indexer) {
        addCommands(
            new Shoot(shooter),

            new WaitCommand(1.5),

            new IndexIn(indexer)
        );
    }
}
