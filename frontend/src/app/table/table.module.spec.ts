import { TableModule } from './table.module';

describe('TableModel', () => {
  let tableModule: TableModule;

  beforeEach(() => {
    tableModule = new TableModule();
  });

  it('should create an instance', () => {
    expect(tableModule).toBeTruthy();
  });
});
