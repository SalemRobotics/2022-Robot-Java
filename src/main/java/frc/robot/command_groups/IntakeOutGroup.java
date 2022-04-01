package frc.robot.command_groups;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.IndexOut;
import frc.robot.commands.IntakeOut;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

public class IntakeOutGroup extends ParallelCommandGroup {
    public IntakeOutGroup(Intake intake, Indexer index, Shooter shooter) {
        addCommands(
            new IntakeOut(intake),
            new IndexOut(index, -1.0)
        );
    }
}
