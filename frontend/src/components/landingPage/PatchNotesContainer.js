import React from 'react'

import { Accordion, AccordionSummary, AccordionDetails } from '@material-ui/core';
import ExpandMoreIcon from '@material-ui/icons/ExpandMore';

import { patchNotes } from './LandingPageTexts';

const PatchNotesContainer = () => {

    return (
        <div id='patchNotesContainer' className='container'>
            <div id='patchNotesHeader' className='left-header'>
                <h2 className='title'>
                    Patch Notes
                </h2>
            </div>
            {patchNotes.map((patchNote) => (
                <Accordion key={patchNote.patchVersion}>
                    <AccordionSummary
                        expandIcon={<ExpandMoreIcon />}
                    >
                        {`Path version: ${patchNote.patchVersion}`}
                    </AccordionSummary>
                    <AccordionDetails>
                        <p>{patchNote.patchNoteDetail}</p>
                    </AccordionDetails>
                </Accordion>
            ))}

        </div>
    )
}

export default PatchNotesContainer;