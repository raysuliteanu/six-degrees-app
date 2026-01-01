import { memo } from 'react';
import { Handle, Position } from '@xyflow/react';
import { Card } from '@/components/ui/card';
import { Film } from 'lucide-react';
import type { ConnectionNode } from '@/types/api';

interface MovieNodeProps {
  data: ConnectionNode;
}

export const MovieNode = memo(({ data }: MovieNodeProps) => {
  return (
    <div className="movie-node">
      <Handle type="target" position={Position.Top} />
      <Card className="min-w-[180px] p-3">
        <div className="flex items-center gap-2">
          <Film className="h-5 w-5 flex-shrink-0 text-muted-foreground" />
          <div className="flex-1">
            <h4 className="text-sm font-semibold leading-tight">
              {data.name}
            </h4>
            {data.metadata?.releaseDate && (
              <p className="text-xs text-muted-foreground">
                {new Date(data.metadata.releaseDate).getFullYear()}
              </p>
            )}
          </div>
        </div>
      </Card>
      <Handle type="source" position={Position.Bottom} />
    </div>
  );
});

MovieNode.displayName = 'MovieNode';
