import * as React from 'react';
import { CardElem } from './CardElem';
import Card from '@mui/material/Card';
import { Box, Grid } from '@mui/material';

export default function Order({ order, index, showBigcard }) {

  const card = <CardElem showCardDetails={showCardDetails} index={index} order={order} />;

  function showCardDetails(e) {
    e.preventDefault();
    showBigcard(order);
  }

  return (
    <Box sx={{ flexDirection: 'row', padding: "10px 10px" }} >
      <Card variant="outlined" sx={{ maxWidth: 180, backgroundColor: "#F8EDE3", width: '180px', borderRadius: '8px', minWidth: 150 }}>{card}</Card>
    </Box>

  );
}
