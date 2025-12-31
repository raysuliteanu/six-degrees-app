import { useMemo } from 'react';
import { useQuery } from '@tanstack/react-query';
import {
  ReactFlow,
  Background,
  Controls,
  MarkerType,
} from '@xyflow/react';
import type { Node, Edge } from '@xyflow/react';
import '@xyflow/react/dist/style.css';
import dagre from 'dagre';
import { connectionApi } from '@/services/api/connectionApi';
import { ActorNode } from './ActorNode';
import { MovieNode } from './MovieNode';
import { NoConnectionMessage } from './NoConnectionMessage';
import { Card } from '@/components/ui/card';
import type { ConnectionPath } from '@/types/api';

interface ConnectionGraphProps {
  actor1Id: number;
  actor2Id: number;
}

const nodeTypes = {
  actor: ActorNode,
  movie: MovieNode,
};

const pathColors = ['#3b82f6', '#8b5cf6', '#ec4899', '#f59e0b'];

function getLayoutedElements(
  nodes: Node[],
  edges: Edge[]
): { nodes: Node[]; edges: Edge[] } {
  const dagreGraph = new dagre.graphlib.Graph();
  dagreGraph.setDefaultEdgeLabel(() => ({}));
  dagreGraph.setGraph({ rankdir: 'TB', ranksep: 100, nodesep: 80 });

  nodes.forEach((node) => {
    dagreGraph.setNode(node.id, { width: 200, height: 100 });
  });

  edges.forEach((edge) => {
    dagreGraph.setEdge(edge.source, edge.target);
  });

  dagre.layout(dagreGraph);

  const layoutedNodes = nodes.map((node) => {
    const nodeWithPosition = dagreGraph.node(node.id);
    return {
      ...node,
      position: {
        x: nodeWithPosition.x - 100,
        y: nodeWithPosition.y - 50,
      },
    };
  });

  return { nodes: layoutedNodes, edges };
}

function transformToReactFlow(
  paths: ConnectionPath[]
): { nodes: Node[]; edges: Edge[] } {
  const nodesMap = new Map<string, Node>();
  const edgesMap = new Map<string, Edge>();

  paths.forEach((path, pathIndex) => {
    // Add nodes
    path.nodes.forEach((node) => {
      if (!nodesMap.has(node.id)) {
        nodesMap.set(node.id, {
          id: node.id,
          type: node.type,
          data: node,
          position: { x: 0, y: 0 },
        });
      }
    });

    // Add edges
    path.edges.forEach((edge) => {
      const edgeId = `${edge.from}-${edge.to}`;
      if (!edgesMap.has(edgeId)) {
        edgesMap.set(edgeId, {
          id: edgeId,
          source: edge.from,
          target: edge.to,
          label: edge.label || undefined,
          animated: pathIndex === 0, // Animate first (shortest) path
          style: {
            stroke: pathColors[pathIndex % pathColors.length],
            strokeWidth: pathIndex === 0 ? 3 : 2,
          },
          markerEnd: {
            type: MarkerType.ArrowClosed,
            color: pathColors[pathIndex % pathColors.length],
          },
        });
      }
    });
  });

  return {
    nodes: Array.from(nodesMap.values()),
    edges: Array.from(edgesMap.values()),
  };
}

export function ConnectionGraph({ actor1Id, actor2Id }: ConnectionGraphProps) {
  const {
    data: paths,
    isLoading,
    error,
  } = useQuery({
    queryKey: ['connection', actor1Id, actor2Id],
    queryFn: () => connectionApi.findConnection(actor1Id, actor2Id),
    enabled: !!actor1Id && !!actor2Id,
    staleTime: 10 * 60 * 1000, // 10 minutes
  });

  const { nodes, edges } = useMemo(() => {
    if (!paths || paths.length === 0) {
      return { nodes: [], edges: [] };
    }

    const { nodes: rawNodes, edges: rawEdges } = transformToReactFlow(paths);
    return getLayoutedElements(rawNodes, rawEdges);
  }, [paths]);

  if (isLoading) {
    return (
      <Card className="p-8">
        <div className="flex items-center justify-center">
          <div className="h-8 w-8 animate-spin rounded-full border-4 border-primary border-t-transparent" />
          <p className="ml-3 text-muted-foreground">
            Finding connections...
          </p>
        </div>
      </Card>
    );
  }

  if (error) {
    return (
      <Card className="p-8">
        <p className="text-center text-destructive">
          Failed to load connection data
        </p>
      </Card>
    );
  }

  if (!paths || paths.length === 0) {
    return <NoConnectionMessage />;
  }

  return (
    <Card className="overflow-hidden">
      <div className="h-[600px]">
        <ReactFlow
          nodes={nodes}
          edges={edges}
          nodeTypes={nodeTypes}
          fitView
          attributionPosition="bottom-right"
          minZoom={0.5}
          maxZoom={2}
        >
          <Background />
          <Controls />
        </ReactFlow>
      </div>
      {paths.length > 0 && (
        <div className="border-t bg-muted/50 px-4 py-3">
          <p className="text-sm text-muted-foreground">
            Found {paths.length} connection{paths.length > 1 ? 's' : ''} •
            Degree of separation: {paths[0].degree}
          </p>
        </div>
      )}
    </Card>
  );
}
