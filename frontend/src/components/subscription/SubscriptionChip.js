/* eslint-disable react/prop-types */
import React from 'react';
import {
    Chip,
    FormControl,
    Input,
    InputLabel,
    makeStyles,
    MenuItem,
    Select
} from '@material-ui/core';

const useStyles = makeStyles(() => ({
    formControl: {

        minWidth: 120,
    },
    chips: {
        display: 'flex',
        flexWrap: 'wrap',
    },
    chip: {
        margin: 2,
    },
}));

const SubscriptionChip = (props) => {

    const {
        title,
        value,
        handleChange,
        inputName,
        selectableItems,
        isMultiple
    } = props;

    const classes = useStyles();

    return (
        <FormControl className={classes.formControl} fullWidth>
            <InputLabel id={`${title}MultiChip`} >{title}</InputLabel>
            <Select
                labelId={`${title}MultiChip`}
                id="demo-mutiple-chip"
                multiple={isMultiple}
                value={value}
                onChange={handleChange}
                name={inputName}
                fullWidth
                input={<Input id="select-multiple-chip" />}
                renderValue={(selected) => (

                    <div className={classes.chips}>
                        {
                            (typeof selected == 'string' && selected != null)

                                ? (<Chip key={value} label={value} className={classes.chip} />)

                                : (selected.map((value) => (
                                    <Chip key={value} label={value} className={classes.chip} />
                                )))
                        }
                    </div>
                )}
            >

                {selectableItems.map((itemType) => (
                    <MenuItem key={itemType} value={itemType} >
                        {itemType}
                    </MenuItem>
                ))}
            </Select>
        </FormControl>
    )
}

export default SubscriptionChip;