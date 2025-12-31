import { Card, CardContent } from '@/components/ui/card';
import { AlertCircle } from 'lucide-react';

export function NoConnectionMessage() {
  return (
    <Card className="border-dashed">
      <CardContent className="flex flex-col items-center justify-center py-12">
        <AlertCircle className="mb-4 h-12 w-12 text-muted-foreground" />
        <h3 className="mb-2 text-lg font-semibold">No Connection Found</h3>
        <p className="text-center text-sm text-muted-foreground">
          No connection found within 6 degrees of separation.
          <br />
          Try different actors or check if they have worked together.
        </p>
      </CardContent>
    </Card>
  );
}
