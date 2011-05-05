package de.myToys.shop.maven.plugins.debug;

import java.io.Writer;

import org.apache.maven.shared.dependency.tree.traversal.DependencyNodeVisitor;

/**
 * Displays the dependency tree for conflicting artifacts without requiring conflicts to be resolved
 * first.
 *
 * @author Leonard Ehrenfried
 * @goal conflict
 */
public class ConflictMojo extends AbstractDebugMojo {
	
	public DependencyNodeVisitor getSerializingDependencyNodeVisitor(Writer writer) {
	 return new ConflictDependencyNodeVisitor();
	}

}