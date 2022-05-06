/*** In The Name of Allah ***/
package ghaffarian.progex.java;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ghaffarian.progex.graphs.pdg.ControlDependenceGraph;
import ghaffarian.progex.graphs.pdg.DataDependenceGraph;
import ghaffarian.progex.graphs.pdg.ProgramDependeceGraph;

/**
 * Program Dependence Graph (PDG) builder for Java programs.
 * A Java parser generated via ANTLRv4 is used for this purpose.
 * This implementation is based on ANTLRv4's Visitor pattern.
 * 
 * @author Seyed Mohammad Ghaffarian
 */
public class JavaPDGBuilder {
	
	/**
	 * Builds and returns Program Dependence Graphs (PDG) for each given Java file.
	 */
	public static ProgramDependeceGraph[] buildForAll(String[] javaFilePaths) throws IOException {
		File[] javaFiles = new File[javaFilePaths.length];
		for (int i = 0; i < javaFiles.length; ++i)
			javaFiles[i] = new File(javaFilePaths[i]);
		return buildForAll(javaFiles);
	}
	
	/**
	 * Builds and returns Program Dependence Graphs (PDG) for each given Java file.
	 */
	public static ProgramDependeceGraph[] buildForAll(File[] javaFiles) throws IOException {
		
		ControlDependenceGraph[] ctrlSubgraphs;
		ctrlSubgraphs = new ControlDependenceGraph[javaFiles.length];
		for (int i = 0; i < javaFiles.length; ++i)
			try {
				ctrlSubgraphs[i] = JavaCDGBuilder.build(javaFiles[i]);
			} catch (Exception e) {
				ctrlSubgraphs[i] = null;
			}
        //
		DataDependenceGraph[] dataSubgraphs;
			dataSubgraphs = new DataDependenceGraph[javaFiles.length];
//		dataSubgraphs = JavaDDGBuilder.buildForAll(javaFiles);
		for (int i=0; i< javaFiles.length; i++) {
			try {
				File[] files = new File[1];
				files[0] = javaFiles[i];
				dataSubgraphs[i] = JavaDDGBuilder.buildForAll(files)[0];
			}
			catch (Exception e) {
				dataSubgraphs[i] = null;
			}
		}
        //
		// Join the subgraphs into PDGs
		List<ProgramDependeceGraph> pdgList = new ArrayList<>();
		for (int i = 0; i < javaFiles.length; ++i) {
			if (ctrlSubgraphs[i] != null && dataSubgraphs[i] != null) {
				pdgList.add(new ProgramDependeceGraph(javaFiles[i],
						ctrlSubgraphs[i], dataSubgraphs[i]));
			}
		}
		return pdgList.toArray(new ProgramDependeceGraph[0]);
	}

}

