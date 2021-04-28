import { Button, TableCell, TableRow } from '@material-ui/core';
import React from 'react'
import DeleteIcon from '@material-ui/icons/Delete';
import PropTypes from 'prop-types';
import Tooltip from './Tooltip';

const ValuableItemRow = ({ item, handleClick }) => {

    const { id, svg, name, type, stashId, price, priceType } = item;
    const { max, min, median, mean } = price;

    return (
        <TableRow key={'table-row' + id}>
            <TableCell scope='row' align='center'>
                <Tooltip
                    component={<img loading='lazy' src={svg} className='item-frame' />}
                    item={item}
                />
            </TableCell>
            <TableCell align='center'>{name}</TableCell>
            <TableCell align='center'>{type}</TableCell>
            <TableCell align='center'>{stashId}</TableCell>
            <TableCell align='center'>{`Median: ${median}, Mean: ${mean}, Max: ${max}, Min: ${min}`}</TableCell>
            <TableCell align='center'>{priceType}</TableCell>
            <TableCell align='center'>
                <Button onClick={() => handleClick(id)}
                    variant='contained'
                    color='secondary'
                    size='small'
                    startIcon={<DeleteIcon />}
                >
                    Delete
                </Button>
            </TableCell>
        </TableRow>
    )
}

export default ValuableItemRow;


ValuableItemRow.propTypes = {

    item: PropTypes.object,
    handleClick: PropTypes.func,

}