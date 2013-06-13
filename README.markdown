#debug-maven-plugin

Sadly, if Maven hits a version conflict whilst resolving artifact dependencies the error messages are less than helpful because they do not specify which (transitive) dependencies are causing the conflict. Maven 3 is no better in this regard than Maven 2 - in fact it is worse.

This plugin assists the developer to debug exactly these dependency resolution problems.

It has the following goals (the artifact has been uploaded to Maven Central so will be available without specifying a separate repository):

##`tree`
*Command line*:

    mvn de.mytoys.maven.plugins:debug-maven-plugin:tree

This goal is a slightly modified version of `dependency:resolve`. The difference is that `dependency:resolve` requires an already resolved dependeny tree, which is obviously not available in the case of a conflict.

Hence, the tree shown by this goal **might still contain version conflicts**.

##`conflict`

*Command line*:

    mvn de.mytoys.maven.plugins:debug-maven-plugin:conflict

Shows direct and transitive artifacts and their paths to the current project *sorted by artifactId* if they are determined to possibly contain a version conflict. Again, for this to work the dependency tree is not resolved fully which means this **can contain version conflicts**.

##Shorter command line

If you add the following to your `settings.xml`

    <pluginGroups>
     <pluginGroup>de.mytoys.maven.plugins</pluginGroup>
    </pluginGroups>

you can call the plugin like this:

    mvn debug:conflict

Alternatively you can establish the plugin in your build process

	<build>
		...
	       	<plugins>
				<plugin>
                    <groupId>de.mytoys.maven.plugins</groupId>
                    <artifactId>debug-maven-plugin</artifactId>
                    <version>1.7-SNAPSHOT</version>
                    <configuration>
                        <failOnConflict>false</failOnConflict>
                    </configuration>
                    <executions>
                        <execution>
                            <phase>validate</phase>
                            <goals>
                                <goal>conflict</goal>
                            </goals>
                        </execution>
                    </executions>
                </plugin>
            ...

Setting the configuration failOnConflict to true will cause the build to fail when any conflicts appear (paranoid mode).
Default value for the parameter is false (sane).
