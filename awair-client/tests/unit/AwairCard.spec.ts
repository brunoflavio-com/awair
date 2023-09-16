// AwairCard.spec.ts
import { mount } from '@vue/test-utils'
import AwairCard from '@/components/AwairCard.vue'

describe('AwairCard.vue', () => {
    it('renders the Awair Score when passed', () => {
        const score = 75;
        const wrapper = mount(AwairCard, {
            props: { score }
        })
        expect(wrapper.text()).toContain(`Awair Score: ${score}`)
    })

    // You can add more tests as needed, for example:
    // - Check if the timestamp is displayed correctly
    // - Check if the humidity, temperature, etc., are displayed when passed as props
    // - Test the behavior of the component when the axios call is successful or fails
})
