import { memo } from 'react';
import { Handle, Position } from '@xyflow/react';
import { Card } from '@/components/ui/card';
import { getImageUrl } from '@/utils/imageUrl';
import type { ConnectionNode } from '@/types/api';

interface ActorNodeProps {
  data: ConnectionNode;
}

export const ActorNode = memo(({ data }: ActorNodeProps) => {
  return (
    <div className="actor-node">
      <Handle type="target" position={Position.Top} />
      <Card className="min-w-[200px] p-4">
        <div className="flex items-center gap-3">
          <img
            src={getImageUrl(data.imageUrl, 'w92')}
            alt={data.name}
            className="h-16 w-16 rounded-full object-cover"
            onError={(e) => {
              (e.target as HTMLImageElement).src = '/placeholder-avatar.svg';
            }}
          />
          <div className="flex-1">
            <h3 className="font-bold">{data.name}</h3>
            {data.metadata?.knownFor && (
              <p className="text-sm text-muted-foreground">
                {data.metadata.knownFor}
              </p>
            )}
          </div>
        </div>
      </Card>
      <Handle type="source" position={Position.Bottom} />
    </div>
  );
});

ActorNode.displayName = 'ActorNode';
