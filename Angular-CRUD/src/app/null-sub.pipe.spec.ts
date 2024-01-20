import { NullSubPipe } from './null-sub.pipe';

describe('NullSubPipe', () => {
  it('create an instance', () => {
    const pipe = new NullSubPipe();
    expect(pipe).toBeTruthy();
  });
});
