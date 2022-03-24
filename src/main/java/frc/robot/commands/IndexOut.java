package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Indexer;

public class IndexOut extends CommandBase {
    private final Indexer indexer;
    public IndexOut(Indexer subsystem) {
        indexer = subsystem;
        addRequirements(indexer);
    }

    @Override
    public void execute() {
        indexer.indexOut();
    }

    @Override
    public void end(boolean interrupted) {
        indexer.halt();
    }
}
