package frc.robot.command_groups;

import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.IndexOut;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;

public class StopIntakeGroup extends ParallelRaceGroup {
    public StopIntakeGroup(Intake intake, Indexer indexer) {
        addCommands(
            new IndexOut(indexer, -0.5),
            new WaitCommand(0.5)
        );
    }
}
