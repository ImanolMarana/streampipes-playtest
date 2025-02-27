/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

export default {
    createPipelineTour: {
        id: 'create-pipeline',
        steps: [
            {
                stepId: 'step-1',
                title: 'Pipeline tutorial',
                classes: 'shepherd shepherd-welcome',
                text: "<p>This tour will teach you how to create your first pipeline. You will learn how to select streams, how to connect data processors and sinks and how to start a pipeline.</p> <p>This tour requires a data stream called <b>Tutorial</b> which you've created in the adapter tutorial.</p>Click <b>next</b> to continue.",
                buttons: ['cancel', 'next'],
            },
            {
                stepId: 'step-2',
                title: 'Create pipeline',
                classes: 'shepherd shepherd-welcome',
                text: '<p>In this view, you can see already existing pipelines. To create a new pipeline, click the button above.</p>',
                buttons: ['cancel'],
                attachToElement: '[data-cy="pipelines-navigate-to-editor"]',
                attachPosition: 'left',
            },
            {
                stepId: 'step-3',
                title: 'Pipeline assembly',
                classes: 'shepherd shepherd-welcome',
                text: '<p>The pipeline assembly is the place where you can create pipelines. <br>Pipelines combine data streams, processors and sinks and can be used to trigger alarms, to transform data or to apply data analytics algorithms.<br> Click <b>Next</b> to continue.</p>',
                buttons: ['cancel', 'next'],
            },
            {
                stepId: 'step-4',
                title: 'Pipeline Element Selection',
                text: "<p>Let's start!</p> <p>This is the <b>Pipeline Element Selection</b> panel. Here you can select and use all available pipeline elements.</p>",
                attachToElement: '#editor-icon-stand',
                attachPosition: 'left',
                buttons: ['cancel', 'next'],
            },
            {
                stepId: 'step-5',
                title: 'Selecting data streams',
                text: "The first element of a pipeline is a data stream, which produces data you'd like to transform. <p>To select a stream, drag and drop the stream named <b>Tutorial</b> to the assembly area on the right.</p>",
                attachToElement: '#tutorial',
                attachPosition: 'left',
                buttons: ['cancel'],
            },
            {
                stepId: 'step-6',
                title: 'Creating pipelines',
                text: '<p>Cool!</p> <p>Dragging and dropping elements is the basic principle you need to know to create pipelines. You only need to select a pipeline element and drop it to the assembly area.</p><p>Click <b>Next</b> to continue.</p>',
                buttons: ['cancel', 'next'],
            },
            {
                stepId: 'step-7',
                title: 'Data processors',
                text: 'Now we will add a data processor. This is the data processor list which shows all currently available data processors.<p>Click <b>Next</b> to continue.</p>',
                attachToElement: '#panel-1',
                attachPosition: 'left',
                buttons: ['cancel', 'next'],
            },
            {
                stepId: 'step-8',
                title: 'Selecting data processors',
                text: '<p>Processors can provide simple capabilities such as filters or aggregations, but can also provide more advanced capabilities such as trend and pattern detection or even pre-trained neural networks.</p><p>Select the processor called <b>Numerical Filter</b> and move it to the assembly area.</p>',
                attachToElement:
                    '#org\\.apache\\.streampipes\\.processors\\.filters\\.jvm\\.numericalfilter',
                attachPosition: 'right',
                buttons: ['cancel'],
            },
            {
                stepId: 'step-9',
                title: 'Connecting elements',
                text: "<p>Now it's time to connect the first elements of your pipeline!</p> <p>Click the gray output port of the stream and connect it with the Numerical Filter component.</p>",
                attachToElement:
                    '#assembly>div.jsplumb-endpoint:nth-of-type(1)',
                attachPosition: 'bottom',
                buttons: ['cancel'],
            },
            {
                stepId: 'step-10',
                title: 'Customize elements',
                text: '<p>Most pipeline elements can be customized according to your needs. Whenever you connect two elements with each other, a configuration dialog pops up.</p><p>Select <b>Mass Flow</b> as the field to filter, select <b>greater than (>) </b> as operation, select <b>2</b> as the threshold value and click <b>Save</b>.</p>',
                attachToElement: 'div .dialog-panel-content',
                attachPosition: 'right',
                buttons: ['cancel'],
            },
            {
                stepId: 'step-11',
                title: 'Selecting data sinks',
                text: "<p>What's missing?</p><p>Every pipeline needs a data sink. Sinks define what to do with the output of your pipeline and can be visualizations, notifications, or can trigger third party components or even actuators.</p><p>Click <b>Next</b> to continue.</p>",
                attachToElement: '#panel-2',
                attachPosition: 'left',
                buttons: ['cancel', 'next'],
            },
            {
                stepId: 'step-12',
                title: 'Finish Pipeline',
                text: '<p>Almost there!</p>Select the <b>Data Lake</b> sink and connect the <b>Numerical Filter</b> to the Data Lake sink. Enter any identifier as the storage name in the configuration menu.</p>',
                attachToElement:
                    '#org\\.apache\\.streampipes\\.sinks\\.internal\\.jvm\\.datalake',
                attachPosition: 'left',
                buttons: ['cancel'],
            },
            {
                stepId: 'step-13',
                title: 'Save Pipeline',
                text: "<p>Great!</p><p>Your first pipeline is complete. No we're ready to start the pipeline.</p><p>Click the <b>Save</b> icon to open the save dialog.</p>",
                attachToElement:
                    '.pipeline-assembly-options>div>button:nth-of-type(1)',
                attachPosition: 'left',
                buttons: ['cancel'],
            },
            {
                stepId: 'step-14',
                title: 'Enter pipeline name',
                text: '<p>Enter a name and an optional description of your pipeline.</p><p>Afterwards, make sure that <b>Start pipeline immediately</b> is checked.</p>',
                attachToElement: 'div .dialog-panel-content',
                attachPosition: 'right',
                buttons: ['cancel'],
            },
            {
                stepId: 'step-15',
                title: 'Save Pipeline Dialog',
                text: '<p>Click on <b>Save and go to pipeline view</b> to start the pipeline.</p>',
                attachToElement: '[data-cy="sp-editor-save"]',
                attachPosition: 'top',
                buttons: ['cancel'],
            },
            {
                stepId: 'step-16',
                title: 'Pipeline Started',
                text: "<p>Congratulations!</p><p>You've completed the pipeline tutorial.</p>",
                attachToElement:
                    'md-dialog>form>md-dialog-actions>button:nth-of-type(1)',
                attachPosition: 'bottom',
                buttons: ['cancel'],
            },
        ],
        matchingSteps: [
            { actionId: 'pipeline-new-button-clicked', currentStep: 'step-2' },
            { actionId: 'drop-stream', currentStep: 'step-5' },
            { actionId: 'select-sepa', currentStep: 'step-7' },
            { actionId: 'drop-sepa', currentStep: 'step-8' },
            { actionId: 'customize-sepa', currentStep: 'step-9' },
            { actionId: 'save-sepa', currentStep: 'step-10' },
            { actionId: 'select-action', currentStep: 'step-11' },
            { actionId: 'save-action', currentStep: 'step-12' },
            { actionId: 'enter-pipeline-name', currentStep: 'step-13' },
            { actionId: 'save-pipeline-dialog', currentStep: 'step-14' },
            { actionId: 'pipeline-started', currentStep: 'step-15' },
        ],
    },
};
