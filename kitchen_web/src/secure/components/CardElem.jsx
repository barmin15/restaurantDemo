import React from "react";
import CardContent from '@mui/material/CardContent';
import Typography from '@mui/material/Typography';
import { Button, CardActions } from '@mui/material';

export function CardElem({
  index, order, showCardDetails
}) {


  return <React.Fragment>
    <CardContent align="center">
      <Typography sx={{
        fontSize: 30
      }} color="text.secondary" gutterBottom>
        order {index + 1}
      </Typography>
      <Typography variant="h5" component="div" sx={{
        fontSize: 25
      }}>
        {order.table.tableName}
      </Typography>
      <Typography sx={{
        mb: 1.5,
        fontSize: 20
      }} color="text.secondary">
        {order.orderStatus}
      </Typography>
      <Typography variant="body2" sx={{
        fontSize: 20
      }}>
        Foods:
        <br />
        {order.orderedFoods ? order.orderedFoods.length : 0}
      </Typography>
      <Typography variant="body2" sx={{
        fontSize: 20
      }}>
        Drinks:
        <br />
        {order.orderedDrinks ? order.orderedDrinks.length : 0}
      </Typography>
    </CardContent>
    <CardActions
      sx={{
        alignSelf: "stretch",
        display: "flex",
        justifyContent: "center",
        p: 2
      }}>
      <Button
      onClick={showCardDetails}
        size="small"
        sx={{
          color: "black"
        }}>View order</Button>
    </CardActions>
  </React.Fragment>;
}
