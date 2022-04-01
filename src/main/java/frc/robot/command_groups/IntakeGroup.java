package frc.robot.command_groups;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.IndexIn;
import frc.robot.commands.IntakeIn;
import frc.robot.commands.Shoot;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

public class IntakeGroup extends ParallelCommandGroup {
    public IntakeGroup(Intake intake, Indexer indexer, Shooter shooter) {
        addCommands(
            new IntakeIn(intake),
            new IndexIn(indexer, 0.5),
            new Shoot(shooter, -1000)
        );
    }
}
