package de.myToys.shop.maven.plugins.debug;

import java.io.Writer;
import org.apache.maven.shared.dependency.tree.DependencyNode;
import org.apache.maven.shared.dependency.tree.traversal.BuildingDependencyNodeVisitor;

import org.apache.maven.shared.dependency.tree.traversal.DependencyNodeVisitor;

/**
 * Displays the dependency tree for conflicting artifacts without requiring conflicts to be resolved
 * first.
 *
 * @author <a href="mailto:leonard.ehrenfried@web.de">Leonard Ehrenfried</a>
 * @goal conflict
 */
public class ConflictMojo extends AbstractDebugMojo {
	
	public DependencyNodeVisitor getSerializingDependencyNodeVisitor(Writer writer) {
	 return new ConflictDependencyNodeVisitor(writer);
	}
	
	@Override
	protected String serializeDependencyTree(DependencyNode rootNode) {
		ConflictDependencyNodeVisitor conflictVisitor = new ConflictDependencyNodeVisitor(null);
		DependencyNodeVisitor buildingVisitor = new BuildingDependencyNodeVisitor(conflictVisitor);
		rootNode.accept(buildingVisitor);
		
		return conflictVisitor.getConflictInfo();
	}

}